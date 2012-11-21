package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseConnectionService {

	public void connect(DataBase dataBase) throws Exception;
	public Connection getConnection() throws DatabaseConnectionException;
	public void test(DataBase dataBase) throws ClassNotFoundException, SQLException;
    public List<String> getAllDbs() throws DatabaseConnectionException;
    public List<String> getAllSchemas() throws DatabaseConnectionException;
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException;
    public DataBase getConnectionDetails();
	
}
