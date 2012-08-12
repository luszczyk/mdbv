package net.luszczyk.mdbv.common.table;

import java.util.List;

public class Entity {

	private Integer id;
	private List<Domain> values;

	public Entity(final Integer id, final List<Domain> values) {
		this.setId(id);
		this.values = values;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Domain> getValues() {
		return values;
	}

	public void setValues(List<Domain> values) {
		this.values = values;
	}
}
