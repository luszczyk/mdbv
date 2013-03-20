package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.AbstractDataBase;
import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.service.DatabaseConnectionHolder;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Table;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("DatabaseConnectionHolder")
public class DatabaseConnectionHolderImpl implements DatabaseConnectionHolder {

    private DataBase dataBase;

    @Autowired
    private QueryService queryService;

    @Autowired
    private RegisterService registerService;

    private Connection conn;

    public void test(DataBase testDataBase) throws ClassNotFoundException,
            SQLException {

        Class.forName(testDataBase.getDriverPackage());
        String url = "jdbc:" + testDataBase.getDriverName() + "://"
                + testDataBase.getDataBaseDTO().getHost() + ":" + testDataBase.getDataBaseDTO().getPort().toString() + "/"
                + testDataBase.getDataBaseDTO().getName();
        DriverManager.getConnection(url, testDataBase.getDataBaseDTO().getUser(),
                testDataBase.getDataBaseDTO().getPass());
    }

    @Override
    public List<String> getAllDbs() throws DatabaseConnectionException {
        return dataBase.getDataBaseQueryExecutor().getAllDbs(getConnection());
    }

    @Override
    public List<String> getAllSchemas() throws DatabaseConnectionException {
        return dataBase.getDataBaseQueryExecutor().getAllSchemas(getConnection());
    }

    @Override
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException {
        return dataBase.getDataBaseQueryExecutor().getAllTablesForSchema(getConnection(), schema);
    }

    @Override
    public DataBase getConnectionDetails() {
        return dataBase;
    }

    public void connect(DataBaseDTO dataBaseDTO) throws SQLException,
            ClassNotFoundException {

        dataBase = AbstractDataBase.createDataBase(dataBaseDTO);

        Class.forName(dataBase.getDriverPackage());
        String url = "jdbc:" + dataBase.getDriverName() + "://"
                + dataBase.getDataBaseDTO().getHost() + ":" + dataBase.getDataBaseDTO().getPort().toString() + "/"
                + dataBase.getDataBaseDTO().getName();
        conn = DriverManager.getConnection(url, dataBase.getDataBaseDTO().getUser(),
                dataBase.getDataBaseDTO().getPass());
    }

    @Override
    public void disconnect() throws Exception {
        this.dataBase = null;
        getConnection().close();
    }

    public Connection getConnection() throws DatabaseConnectionException {

        if (conn == null) {
            throw new DatabaseConnectionException("No connected database");
        } else {

            try {
                if (conn.isClosed()) {
                    throw new DatabaseConnectionException("Connection closed");
                }
            } catch (Exception e) {
                throw new DatabaseConnectionException("Connection error can't check if is closed !");
            }

            return conn;
        }
    }

    @Override
    public Domain getDomain(Column column, ResultSet resultSet, Table table) throws DatabaseConnectionException, SQLException {

        return dataBase.getDataBaseQueryExecutor().getDomain(registerService, getConnection(), column, resultSet, table);
    }

    public byte[] getContentByte(final Domain domain, final Integer size) throws SQLException, DatabaseConnectionException {

        return dataBase.getDataBaseQueryExecutor().getContentByte(getConnection(), domain, size);
    }
}
