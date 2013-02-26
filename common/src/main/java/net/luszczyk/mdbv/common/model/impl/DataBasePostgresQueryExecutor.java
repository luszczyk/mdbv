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
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class DataBasePostgresQueryExecutor implements DataBaseQueryExecutor {

    private static final Logger LOGGER = Logger.getLogger(DataBasePostgresQueryExecutor.class);
    private LargeObjectManager largeObjectManager;

    @Override
    public List<String> getAllDbs(Connection conn) throws DatabaseConnectionException {

        String query = "SELECT datname FROM pg_database WHERE datistemplate = false";

        List<String> result = new ArrayList<String>();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
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

    @Override
    public List<String> getAllSchemas(Connection conn) throws DatabaseConnectionException {

        String query = "SELECT table_schema FROM information_schema.tables ORDER BY table_schema;";

        Set<String> result = new HashSet<String>();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.debug("Error processing query: " + query, e);
            return null;
        }

        return new ArrayList<String>(result);
    }

    @Override
    public List<String> getAllTablesForSchema(Connection conn, String schema) throws DatabaseConnectionException {

        String query = "SELECT table_schema, table_name FROM information_schema.tables WHERE table_schema = '" + schema + "' ORDER BY table_name;";

        List<String> result = new ArrayList<String>();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.debug("Error processing query: " + query, e);
            return null;
        }

        return result;
    }

    public Domain getDomain(RegisterService registerService, Connection conn, Column c, ResultSet rs, Table table) throws SQLException, DatabaseConnectionException {

        Domain d = null;

        if ("oid".equals(c.getType())) {
            Long oid = rs.getLong(c.getId());
            d = new Domain(table, c, oid.toString(), oid);
            fillDetails(d, conn, registerService);
        } else if ("geometry".equals(c.getType())) {
            WKBReader wkbReader = new WKBReader();
            try {
                Geometry geo = wkbReader.read(WKBReader.hexToBytes(rs.getString(c.getId())));
                String geoText = geo.toText();
                d = new Domain(table, c, geoText);
                fillDetailsByWktParams(d, registerService);
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                d = null;
            }
        } else {
            d = new Domain(table, c, rs.getObject(c.getId()) == null ? "" : rs.getObject(c.getId()).toString());
        }
        return d;
    }

    private LargeObjectManager getLargeObjectManager(Connection conn) throws DatabaseConnectionException, SQLException {

        if (largeObjectManager == null) {
            largeObjectManager = ((org.postgresql.PGConnection) conn).getLargeObjectAPI();
        }
        return largeObjectManager;
    }

    public byte[] getContentByte(Connection conn, Domain domain, final Integer size) throws SQLException, DatabaseConnectionException {

        Assert.notNull(domain);
        Long domainOid = domain.getOid();
        Assert.notNull(domainOid);
        byte[] result = null;

        LargeObject obj = getLargeObjectManager(conn).open(domainOid, LargeObjectManager.READ);

        int len;

        if (size == null || obj.size() < size) {
            len = obj.size();
        } else {
            len = size;
        }

        result = new byte[len];
        obj.read(result, 0, len);
        obj.close();

        return result;
    }

    private void fillDetailsByWktParams(Domain d, RegisterService registerService) {

        String type = "map/wkt";
        fillDetailsByType(type, d, registerService);
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

        if (type == null) {
            System.out.println(Hex.encodeHexString(buf));
            for (Byte b : buf) {

                System.out.println(Integer.toHexString(b & 0xFF));
            }
        }

        fillDetailsByType(type, domain, registerService);
    }

    private void fillDetailsByType(String type, Domain domain, RegisterService registerService) {

        if (type != null) {

            domain.setMimeType(type);
            ViewerService viewerService = registerService.getViewerService(type);
            if (viewerService != null) {
                domain.setLinkToView(viewerService.getLink(domain.getId().toString()));
            } else {
                LOGGER.debug("Don't know type: " + type);
            }
        }

    }

}
