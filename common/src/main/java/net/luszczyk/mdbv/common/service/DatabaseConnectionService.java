package net.luszczyk.mdbv.common.service;

import java.sql.Connection;

import net.luszczyk.mdbv.common.model.DataBase;

public interface DatabaseConnectionService {

	public void connect() throws Exception;
	public Connection getConnection();
	public DataBase getDataBase();
	
}
