package net.luszczyk.mdbv.common.model;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.table.Column;

import java.sql.ResultSet;

public interface DataBase {
	
	String getDbName();
	String getHost();
	String getUser();
	String getPass();
	Integer getPort();
	String getDriverName();
	String getDriverPackage();
    DataBaseQueryExecutor getDataBaseQueryExecutor();
    DataBaseDTO getDataBaseDTO();
    Boolean isDbSchemaAvailable();

}
