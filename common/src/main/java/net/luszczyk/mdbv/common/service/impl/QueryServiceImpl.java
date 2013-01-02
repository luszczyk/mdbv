package net.luszczyk.mdbv.common.service.impl;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;
import net.luszczyk.mdbv.common.table.*;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.log4j.Logger;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QueryServiceImpl implements QueryService {

    private static final Logger LOGGER = Logger.getLogger(QueryServiceImpl.class);
    private static final int MAX_HEADER = 16;
    private LargeObjectManager largeObjectManager;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private DatabaseConnectionService databaseConnectionService;

    private LargeObjectManager getLargeObjectManager() throws DatabaseConnectionException, SQLException {

        Connection conn = databaseConnectionService.getConnection();

        if (largeObjectManager == null) {
            largeObjectManager = ((org.postgresql.PGConnection) conn).getLargeObjectAPI();
        }
        return largeObjectManager;
    }

    public Result runQuery(String query) throws DatabaseConnectionException, SQLException {

        Connection conn = databaseConnectionService.getConnection();
        conn.setAutoCommit(false);
        conn.rollback();

        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();

        List<Column> columns = new ArrayList<Column>();
        List<Entity> entities = new ArrayList<Entity>();
        Table table = new Table(rs.getCursorName(), columns, entities);

        for (int i = 1; i <= numberOfColumns; ++i) {
            columns.add(new Column(i, rsmd.getColumnName(i), rsmd
                    .getColumnTypeName(i)));
        }

        Integer id = 0;
        while (rs.next()) {

            List<Domain> objects = new ArrayList<Domain>();
            for (Column c : columns) {
                Domain d;
                if ("oid".equals(c.getType())) {
                    Long oid = rs.getLong(c.getId());
                    d = new Domain(table, c, oid.toString(), oid);
                    fillDetails(d);
                } else if ("geometry".equals(c.getType())) {
                    WKBReader wkbReader = new WKBReader();
                    try {
                        Geometry geo = wkbReader.read(WKBReader.hexToBytes(rs.getString(c.getId())));
                        String geoText = geo.toText();
                        d = new Domain(table, c, geoText);
                        fillDetailsByWktParams(d);
                    } catch (ParseException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        d = null;
                    }
                } else {
                    d = new Domain(table, c, rs.getObject(c.getId()) == null ? "" : rs.getObject(c.getId()).toString());
                }

                objects.add(d);
            }
            entities.add(new Entity(++id, objects));
        }

        Result result = new Result(table);
        return result;
    }

    public byte[] getContentByte(final Domain domain, final Integer size) throws SQLException, DatabaseConnectionException {

        Assert.notNull(domain);
        Long domainOid = domain.getOid();
        Assert.notNull(domainOid);
        byte[] result = null;

        LargeObject obj = getLargeObjectManager().open(domainOid, LargeObjectManager.READ);

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

    @Override
    public List<String> getAllDbs() throws DatabaseConnectionException {

        String query = "SELECT datname FROM pg_database WHERE datistemplate = false";

        Connection conn = databaseConnectionService.getConnection();

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
    public List<String> getAllSchemas() throws DatabaseConnectionException {

        String query = "SELECT table_schema FROM information_schema.tables ORDER BY table_schema;";

        Connection conn = databaseConnectionService.getConnection();

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
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException {

        String query = "SELECT table_schema, table_name FROM information_schema.tables WHERE table_schema = '" + schema + "' ORDER BY table_name;";

        Connection conn = databaseConnectionService.getConnection();

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


    private void fillDetailsByWktParams(Domain d) {

        String type = "map/wkt";
        fillDetailsByType(type, d);
    }

    private void fillDetails(Domain domain) throws SQLException, DatabaseConnectionException {

        byte buf[] = getContentByte(domain, MAX_HEADER);

        String type = null;
        try {
            MagicMatch magicMatch = Magic.getMagicMatch(buf);
            type = magicMatch.getMimeType();
        } catch (Exception e1) {
            LOGGER.debug("MegicMatch didn't match the type !", e1);
        }

        fillDetailsByType(type, domain);
    }

    private void fillDetailsByType(String type, Domain domain) {

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
