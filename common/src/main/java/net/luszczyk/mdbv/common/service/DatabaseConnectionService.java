package net.luszczyk.mdbv.common.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.luszczyk.mdbv.common.model.DataBase;

public interface DatabaseConnectionService {

	public void connect(DataBase dataBase) throws Exception;
	public Connection getConnection() throws Exception;
	public void test(DataBase dataBase) throws ClassNotFoundException,
	SQLException ;
	
	
}
