package net.luszczyk.mdbv.common.model.impl;

import java.util.List;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.table.Table;

public class DataBasePostgres implements DataBase {

	private String host;
	private String user;
	private String pass;
	private Integer port;
	private String name;
	
	public DataBasePostgres() {};

	public DataBasePostgres(String host, String user, String pass, Integer port) {
		super();
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.port = port;
		this.name = "mdbvdb";
	}


	public DataBasePostgres(String host, String user, String pass, Integer port, String name) {
		super();
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.port = port;
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setPort(Integer port) {
		this.port = port;
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
