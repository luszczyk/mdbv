package net.luszczyk.mdbv.common;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private List<Column> columns = new ArrayList<Column>();
	private List<Entity> entities = new ArrayList<Entity>();

	public Table(List<Column> columns, List<Entity> entities) {
		super();
		this.columns = columns;
		this.entities = entities;
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
