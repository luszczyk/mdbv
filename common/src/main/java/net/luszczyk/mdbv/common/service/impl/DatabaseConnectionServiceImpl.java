package net.luszczyk.mdbv.common.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;

import org.springframework.stereotype.Service;

@Service("DatabaseConnectionService")
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

	/*
	 * private DataBase dataBase = new DataBasePostgres("kapitanlamp", "mdbv",
	 * "mdbvdupa", 3307, "mdbvdb");
	 */

	private Connection conn;

	public void test(DataBase testDataBase) throws ClassNotFoundException,
			SQLException {

		Class.forName(testDataBase.getDriverPackage());
		String url = "jdbc:" + testDataBase.getDriverName() + "://"
				+ testDataBase.getHost() + "/" + testDataBase.getDbName();
		DriverManager.getConnection(url, testDataBase.getUser(),
				testDataBase.getPass());
	}

	public void connect(DataBase dataBase) throws SQLException,
			ClassNotFoundException {

		Class.forName(dataBase.getDriverPackage());
		String url = "jdbc:" + dataBase.getDriverName() + "://"
				+ dataBase.getHost() + "/" + dataBase.getDbName();
		conn = DriverManager.getConnection(url, dataBase.getUser(),
				dataBase.getPass());
	}

	public Connection getConnection() throws Exception {

		if (conn == null) {
			throw new Exception("No connected database");
		} else {

			return conn;
		}
	}
}
