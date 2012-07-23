package net.luszczyk.mdbv.common;

public class Column {

	private int id;
	private String name;
	private String type;

	public Column(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
