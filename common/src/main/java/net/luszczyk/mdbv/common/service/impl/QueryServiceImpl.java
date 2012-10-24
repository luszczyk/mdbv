package net.luszczyk.mdbv.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.luszczyk.mdbv.common.exception.GettingLargeObjectException;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.service.FileService;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.DomainDetails;
import net.luszczyk.mdbv.common.table.DomainProxy;
import net.luszczyk.mdbv.common.table.Entity;
import net.luszczyk.mdbv.common.table.Result;
import net.luszczyk.mdbv.common.table.Table;

import org.apache.log4j.Logger;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {

    private static final Logger LOGGER = Logger.getLogger(QueryServiceImpl.class);

    private LargeObjectManager lobj;

    @Autowired
    private FileService fileService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private DatabaseConnectionService databaseConnectionService;

    private LargeObjectManager getLargeObjectManager() {

        try {

            Connection conn = databaseConnectionService.getConnection();

            if (lobj == null) {
                lobj = ((org.postgresql.PGConnection) conn).getLargeObjectAPI();
            }
        } catch (Exception e) {
            LOGGER.error("Error getting LargeObjectManager", e);
        }
        return lobj;
    }

    public Result runQuery(String query) {

        Result result = null;
        try {
            Connection conn = databaseConnectionService.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            List<Column> columns = new ArrayList<Column>();
            List<Entity> entities = new ArrayList<Entity>();
            Table table = new Table(rs.getCursorName(), columns, entities);

            for (int i = 1; i < numberOfColumns; ++i) {
                columns.add(new Column(i, rsmd.getColumnName(i), rsmd
                        .getColumnTypeName(i)));
            }

            Integer id = 0;
            while (rs.next()) {

                List<Domain> objects = new ArrayList<Domain>();
                for (Column c : columns) {
                    Domain d = null;
                    if ("oid".equals(c.getType())) {
                        d = new DomainProxy(this, table, c, rs.getObject(c.getId())
                                .toString(), rs.getLong(c.getId()));
                    } else {
                        d = new DomainProxy(this, table, c, rs.getObject(c.getId())
                                .toString());
                    }

                    objects.add(d);
                }
                entities.add(new Entity(++id, objects));
            }

            result = new Result(table);

        } catch (Exception e) {
            LOGGER.error("Error execution query: " + query, e);
        }
        return result;
    }

    public DomainDetails getDomainDetails(final Domain domain)
            throws GettingLargeObjectException {

        DomainDetails result = null;

        if (domain.getOid() != null) {

            long oid = domain.getOid();
            LargeObject obj = null;

            try {
                obj = getLargeObjectManager().open(oid, LargeObjectManager.READ);

                byte buf[] = new byte[obj.size()];
                obj.read(buf, 0, obj.size());
                obj.close();

                String filePath = fileService.saveFile(buf);

                if (filePath != null) {

                    String type = fileService.getFileType(filePath);

                    if (type != null) {

                        if (registerService.getTypeList().contains(type)) {

                            result = new DomainDetails(filePath, type,
                                    registerService.getViewerService(type));
                        } else {

                            result = new DomainDetails(filePath, type);
                        }
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("Error getting largeobject oid: " + oid, e);
                throw new GettingLargeObjectException();
            }
        } else {
            throw new GettingLargeObjectException();
        }
        return result;
    }
}
