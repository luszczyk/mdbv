package net.luszczyk.mdbv.common.table;

import java.util.List;

public class Entity {

	private List<Domain> values;
	private Column column;

	public Entity(List<Domain> values) {
		this.values = values;
	}

	public List<Domain> getValues() {
		return values;
	}

	public void setValues(List<Domain> values) {
		this.values = values;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

}
