package net.luszczyk.mdbv.common;

import net.luszczyk.mdbv.common.service.ViewerService;

public interface Viewable {

	boolean isViewable();

	ViewerService getViewerService();

	String getLink();

}
