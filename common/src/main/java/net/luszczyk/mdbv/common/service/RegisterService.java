package net.luszczyk.mdbv.common.service;

import java.util.Set;

public interface RegisterService {
	
	Set<String> getTypeList();
	ViewerService getViewerService(String type);
	void addTypeAndViewer(String type, ViewerService viewer);
	
}
