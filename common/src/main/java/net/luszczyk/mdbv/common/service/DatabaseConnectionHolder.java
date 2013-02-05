package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseConnectionHolder {

	public void connect(DataBaseDTO dataBase) throws Exception;
    public void disconnect() throws Exception;
	public Connection getConnection() throws DatabaseConnectionException;
	public void test(DataBase dataBase) throws ClassNotFoundException, SQLException;
    public List<String> getAllDbs() throws DatabaseConnectionException;
    public List<String> getAllSchemas() throws DatabaseConnectionException;
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException;
    public DataBase getConnectionDetails();
    public Domain getDomain(Column column, ResultSet resultSet, Table table) throws DatabaseConnectionException, SQLException;
    public byte[] getContentByte(Domain domain, Integer size) throws SQLException, DatabaseConnectionException;
	
}
