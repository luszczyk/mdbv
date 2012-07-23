package net.luszczyk.mdbv.common;

public class Domain implements Viewable {

	private Object value;
	private String type;
	private boolean viewable;

	public Domain(Object value, String type) {
		super();
		this.value = value;
		this.type = type;
		this.viewable = false;
	}
	
	public Domain(Object value, String type, boolean viewable) {
		super();
		this.value = value;
		this.type = type;
		this.viewable = viewable;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean isViewable() {
		return viewable;
	}

}
