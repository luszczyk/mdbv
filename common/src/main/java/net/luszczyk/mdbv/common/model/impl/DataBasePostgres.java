package net.luszczyk.mdbv.common.model.impl;

import java.util.List;

import net.luszczyk.mdbv.common.Table;
import net.luszczyk.mdbv.common.model.DataBase;

public class DataBasePostgres implements DataBase {

	private String host;
	private String user;
	private String pass;
	private Integer port;
	private String name;
	
	public DataBasePostgres(String host, String user, String pass, Integer port, String name) {
		super();
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.port = port;
		this.name = name;
	}
	
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPass() {
		return pass;
	}

	@Override
	public Integer getPort() {
		return port;
	}

	@Override
	public List<Table> getTables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDriverName() {
		return "postgresql";
	}

	@Override
	public String getDriverPackage() {
		return "org.postgresql.Driver";
	}

	@Override
	public String getDbName() {
		return name;
	}
	
}
