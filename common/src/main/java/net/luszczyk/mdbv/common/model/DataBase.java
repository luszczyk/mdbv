package net.luszczyk.mdbv.common.model;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.table.Column;

import java.sql.ResultSet;

public interface DataBase {

	String getDriverName();
	String getDriverPackage();
    DataBaseQueryExecutor getDataBaseQueryExecutor();
    DataBaseDTO getDataBaseDTO();
    Boolean isDbSchemaAvailable();

}
