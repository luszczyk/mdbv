package net.luszczyk.mdbv.common;

import net.luszczyk.mdbv.common.service.ViewerService;

public class Domain implements Viewable {

	private Object value;
	private String type;
	private ViewerService viewerService;

	public Domain(final Object value, final String type) {
		super();
		this.value = value;
		this.type = type;
	}

	public Domain(final Object value, final String type,
			final ViewerService viewerService) {
		super();
		this.value = value;
		this.type = type;
		this.viewerService = viewerService;
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
		if (viewerService == null) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	@Override
	public ViewerService getViewerService() {
		return viewerService;
	}

	@Override
	public String getLink() {
		return viewerService.getLink(value.toString());
	}
}
