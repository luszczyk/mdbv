package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.service.DatabaseConnectionHolder;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.table.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private DatabaseConnectionHolder databaseConnectionHolder;

    public Result runQuery(String query) throws DatabaseConnectionException, SQLException {

        Connection conn = databaseConnectionHolder.getConnection();
        conn.setAutoCommit(false);
        conn.rollback();

        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();

        List<Column> columns = new ArrayList<Column>();
        List<Entity> entities = new ArrayList<Entity>();
        Table table = new Table("", columns, entities);

        for (int i = 1; i <= numberOfColumns; ++i) {
            columns.add(new Column(i, rsmd.getColumnName(i), rsmd
                    .getColumnTypeName(i)));
        }

        Integer id = 0;
        while (rs.next()) {

            List<Domain> objects = new ArrayList<Domain>();
            for (Column c : columns) {
                Domain d =  databaseConnectionHolder.getDomain(c, rs, table);
                objects.add(d);
            }
            entities.add(new Entity(++id, objects));
        }

        Result result = new Result(table);
        return result;
    }

    public byte[] getContentByte(final Domain domain, final Integer size) throws SQLException, DatabaseConnectionException {

        Assert.notNull(domain);
        byte[] result = databaseConnectionHolder.getContentByte(domain, size);

        return result;
    }
}
