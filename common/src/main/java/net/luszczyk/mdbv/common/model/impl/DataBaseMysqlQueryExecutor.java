package net.luszczyk.mdbv.common.model.impl;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.DataBaseQueryExecutor;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseMysqlQueryExecutor implements DataBaseQueryExecutor {
    @Override
    public List<String> getAllDbs(Connection conn) throws DatabaseConnectionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getAllSchemas(Connection conn) throws DatabaseConnectionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getAllTablesForSchema(Connection conn, String schema) throws DatabaseConnectionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Domain getDomain(RegisterService registerService, Connection conn, Column column, ResultSet resultSet, Table table) throws SQLException, DatabaseConnectionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] getContentByte(Connection conn, Domain domain, Integer size) throws SQLException, DatabaseConnectionException {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
