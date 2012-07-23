package net.luszczyk.mdbv.common.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;

@Service("DatabaseConnectionService")
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

	private DataBase dataBase = new DataBasePostgres("kapitanlamp", "mdbv",
			"mdbvdupa", 3307, "mdbvdb");

	private Connection conn;

	public void connect() throws SQLException, ClassNotFoundException {

		Class.forName(dataBase.getDriverPackage());
		String url = "jdbc:" + dataBase.getDriverName() + "://"
				+ dataBase.getHost() + "/" + dataBase.getDbName();
		conn = DriverManager.getConnection(url, dataBase.getUser(),
				dataBase.getPass());
	}

	public Connection getConnection() {

		if (conn == null) {
			try {
				connect();
			} catch (SQLException e) {
				// TODO logger
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

		return conn;
	}
	
	public DataBase getDataBase() {
		return dataBase;
	}

}
