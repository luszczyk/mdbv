package net.luszczyk.mdbv.common.model;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Column;
import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Table;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public interface DataBaseQueryExecutor {

    List<String> getAllDbs(Connection conn) throws DatabaseConnectionException;
    List<String> getAllSchemas(Connection conn) throws DatabaseConnectionException;
    List<String> getAllTablesForSchema(Connection conn, String schema) throws DatabaseConnectionException;
    Domain getDomain(RegisterService registerService, Connection conn, Column column, ResultSet resultSet, Table table) throws SQLException, DatabaseConnectionException;
    byte[] getContentByte(Connection conn, Domain domain, Integer size) throws SQLException, DatabaseConnectionException;
}