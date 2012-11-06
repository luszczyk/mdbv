package net.luszczyk.mdbv.common.model;

public interface DataBase {
	
	String getDbName();
	String getHost();
	String getUser();
	String getPass();
    void setDbName(String dbName);
	Integer getPort();
	String getDriverName();
	String getDriverPackage();

}
