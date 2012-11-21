package net.luszczyk.mdbv.common.service;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Result;

import java.sql.SQLException;
import java.util.List;

public interface QueryService {
	
	public Result runQuery(String query) throws DatabaseConnectionException, SQLException;
    public byte[] getContentByte(Domain domain, Integer size) throws SQLException, DatabaseConnectionException;
    public List<String> getAllDbs() throws DatabaseConnectionException;
    public List<String> getAllSchemas() throws DatabaseConnectionException;
    public List<String> getAllTablesForSchema(String schema) throws DatabaseConnectionException;

}
