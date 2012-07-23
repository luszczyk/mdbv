package net.luszczyk.mdbv.common.model;

import java.util.List;

import net.luszczyk.mdbv.common.Table;

public interface DataBase {
	
	String getDbName();
	String getHost();
	String getUser();
	String getPass();
	Integer getPort();
	String getDriverName();
	String getDriverPackage();
	List<Table> getTables();

}
