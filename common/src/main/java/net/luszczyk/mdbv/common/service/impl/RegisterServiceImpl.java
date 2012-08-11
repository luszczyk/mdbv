package net.luszczyk.mdbv.common.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;

import org.apache.log4j.Logger;

public class RegisterServiceImpl implements RegisterService {
	
	private static final Logger LOGGER = Logger.getLogger(RegisterServiceImpl.class);
	
	public RegisterServiceImpl() {
		LOGGER.debug("RegisterService const");
	}

	Map<String, ViewerService> viewerMap = new HashMap<String, ViewerService>();

	@Override
	public Set<String> getTypeList() {
		return viewerMap.keySet();
	}

	@Override
	public ViewerService getViewerService(String type) {
		return viewerMap.get(type);
	}
	
	@Override
	public void addTypeAndViewer(String type, ViewerService viewer) {
		viewerMap.put(type, viewer);
	}

}
