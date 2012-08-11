package net.luszczyk.mdbv.common.table;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private List<Column> columns = new ArrayList<Column>();
	private List<Entity> entities = new ArrayList<Entity>();

	public Table(String name, List<Column> columns, List<Entity> entities) {
		this.name = name;
		this.columns = columns;
		this.entities = entities;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public int getSize() {
		return columns.size();
	}
}
