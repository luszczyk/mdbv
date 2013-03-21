package net.luszczyk.mdbv.common.model.impl;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.DataBaseQueryExecutor;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Table;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseMysqlQueryExecutor implements DataBaseQueryExecutor {

    private static final Logger LOGGER = Logger.getLogger(DataBaseMysqlQueryExecutor.class);
    private static final String SELECT_ALL_DB_QUERY = "SELECT SCHEMA_NAME AS `Database` FROM INFORMATION_SCHEMA.SCHEMATA";
    private static final String SELECT_ALL_TABLES_QUERY = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?";

    private List<String> executeListQuery(Connection conn, String query, String param) throws DatabaseConnectionException {

        List<String> result = new ArrayList<String>();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            if (param != null) {
                ps.setString(1, param);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.debug("Error processing query: " + query, e);
            return null;
        }
        return result;
    }

    private List<String> executeListQuery(Connection conn, String query) throws DatabaseConnectionException {

        return executeListQuery(conn, query, null);
    }

    @Override
    public List<String> getAllDbs(Connection conn) throws DatabaseConnectionException {

        return executeListQuery(conn, SELECT_ALL_DB_QUERY);
    }

    @Override
    public List<String> getAllSchemas(Connection conn) throws DatabaseConnectionException {

        //returns null because mysql doesn't support schemas
        return null;
    }

    @Override
    public List<String> getAllTablesForSchema(Connection conn, String schema) throws DatabaseConnectionException {

        return executeListQuery(conn, SELECT_ALL_TABLES_QUERY, schema);
    }

    @Override
    public Domain getDomain(RegisterService registerService, Connection conn, Column c, ResultSet rs, Table table) throws SQLException, DatabaseConnectionException {

        Domain d = null;

        if ("MEDIUMBLOB".equals(c.getType().toUpperCase()) || "BLOB".equals(c.getType().toUpperCase())) {

            Blob blob = rs.getBlob(c.getId());
            d = new Domain(table, c, blob.toString(), blob, null, null);
            fillDetails(d, conn, registerService);
        } else if ("GEOMETRY".equals(c.getType().toUpperCase())) {
            try {
                Geometry geo = getGeometryFromInputStream(rs.getBinaryStream(c.getId()));
                String geoText = geo.toText();
                d = new Domain(table, c, geoText);
                fillDetailsByWktParams(d, registerService);
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                d = null;
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                d = null;
            }
        } else {
            d = new Domain(table, c, rs.getObject(c.getId()) == null ? "" : rs.getObject(c.getId()).toString());
        }
        return d;
    }

    private Geometry getGeometryFromInputStream(InputStream inputStream) throws Exception {

        Geometry dbGeometry = null;

        if (inputStream != null) {

            //convert the stream to a byte[] array
            //so it can be passed to the WKBReader
            byte[] buffer = new byte[255];

            int bytesRead = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            byte[] geometryAsBytes = baos.toByteArray();

            if (geometryAsBytes.length < 5) {
                throw new Exception("Invalid geometry inputStream - less than five bytes");
            }

            //first four bytes of the geometry are the SRID,
            //followed by the actual WKB.  Determine the SRID
            //here
            byte[] sridBytes = new byte[4];
            System.arraycopy(geometryAsBytes, 0, sridBytes, 0, 4);
            boolean bigEndian = (geometryAsBytes[4] == 0x00);

            int srid = 0;
            if (bigEndian) {
                for (int i = 0; i < sridBytes.length; i++) {
                    srid = (srid << 8) + (sridBytes[i] & 0xff);
                }
            } else {
                for (int i = 0; i < sridBytes.length; i++) {
                    srid += (sridBytes[i] & 0xff) << (8 * i);
                }
            }

            //use the JTS WKBReader for WKB parsing
            WKBReader wkbReader = new WKBReader();

            //copy the byte array, removing the first four
            //SRID bytes
            byte[] wkb = new byte[geometryAsBytes.length - 4];
            System.arraycopy(geometryAsBytes, 4, wkb, 0, wkb.length);
            dbGeometry = wkbReader.read(wkb);
            dbGeometry.setSRID(srid);
        }

        return dbGeometry;
    }

    private void fillDetailsByWktParams(Domain d, RegisterService registerService) {

        String type = "map/wkt";
        fillDetailsByType(type, d, registerService);
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    @Override
    public byte[] getContentByte(Connection conn, Domain domain, Integer size) throws SQLException, DatabaseConnectionException {

        Blob blob = domain.getBlob();
        int blobLength = safeLongToInt(blob.length());

        int len;

        if (size == null || blobLength < size) {
            len = blobLength;
        } else {
            len = size;
        }

        byte[] result = blob.getBytes(1, len);

        return result;
    }

    private void fillDetails(Domain domain, Connection conn, RegisterService registerService) throws SQLException, DatabaseConnectionException {

        byte buf[] = getContentByte(conn, domain, MAX_HEADER);

        String type = null;
        try {
            MagicMatch magicMatch = Magic.getMagicMatch(buf);
            type = magicMatch.getMimeType();
        } catch (Exception e1) {
            LOGGER.debug("MegicMatch didn't match the type !", e1);
        }

        domain.setStartBytes(Arrays.toString(Arrays.copyOf(buf, MAX_HEADER)));

        fillDetailsByType(type, domain, registerService);
    }

    private void fillDetailsByType(String type, Domain domain, RegisterService registerService) {

        if (type != null) {

            domain.setMimeType(type);
            ViewerService viewerService = registerService.getViewerService(type);
            if (viewerService != null) {
                domain.setLinkToView(viewerService.getLink(domain.getId().toString()));
                domain.setIcon(viewerService.getIcon());

            } else {
                LOGGER.debug("Don't know type: " + type);
            }
        }

    }
}
