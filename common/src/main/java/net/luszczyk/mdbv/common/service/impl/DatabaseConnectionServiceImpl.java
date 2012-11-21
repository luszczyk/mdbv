package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service("DatabaseConnectionService")
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {


    private DataBase dataBase;

    @Autowired
    private QueryService queryService;

	private Connection conn;

	public void test(DataBase testDataBase) throws ClassNotFoundException,
			SQLException {

		Class.forName(testDataBase.getDriverPackage());
		String url = "jdbc:" + testDataBase.getDriverName() + "://"
				+ testDataBase.getHost()+":"+testDataBase.getPort().toString() + "/" + testDataBase.getDbName();
		DriverManager.getConnection(url, testDataBase.getUser(),
				testDataBase.getPass());
	}

    @Override
    public List<String> getAllDbs() throws DatabaseConnectionException {
        return queryService.getAllDbs();
    }

    @Override
    public List<String> getAllSchemas() throws DatabaseConnectionException {
        return queryService.getAllSchemas();
    }

    @Override
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException {
        return queryService.getAllTablesForSchema(schema);
    }

    @Override
    public DataBase getConnectionDetails() {
        return dataBase;
    }

    public void connect(DataBase dataBase) throws SQLException,
			ClassNotFoundException {

		Class.forName(dataBase.getDriverPackage());
		String url = "jdbc:" + dataBase.getDriverName() + "://"
				+ dataBase.getHost()+":"+dataBase.getPort().toString() + "/" + dataBase.getDbName();
		conn = DriverManager.getConnection(url, dataBase.getUser(),
				dataBase.getPass());
        this.dataBase = dataBase;
	}

	public Connection getConnection() throws DatabaseConnectionException {

		if (conn == null) {
			throw new DatabaseConnectionException("No connected database");
		} else {

            try {
                if(conn.isClosed()) {
                    throw new DatabaseConnectionException("Connection closed");
                }
            }  catch (Exception e) {
                throw new DatabaseConnectionException("Connection error can't check if is closed !");
            }

			return conn;
		}
	}
}
