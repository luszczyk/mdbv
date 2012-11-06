package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.model.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseConnectionService {

	public void connect(DataBase dataBase) throws Exception;
	public Connection getConnection() throws Exception;
	public void test(DataBase dataBase) throws ClassNotFoundException, SQLException;
    public List<String> getAllDbs();
    public List<String> getAllSchemas();
    public List<String> getAllTablesForSchema(String schema);
    public DataBase getConnectionDetails();
	
}
