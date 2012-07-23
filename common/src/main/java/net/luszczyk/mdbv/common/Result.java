package net.luszczyk.mdbv.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Result {

	private Table table;

	public Result(Table table) {
		super();
		this.table = table;
	}

	public void setData(ResultSet rs, ResultSetMetaData rsmd) {

	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
