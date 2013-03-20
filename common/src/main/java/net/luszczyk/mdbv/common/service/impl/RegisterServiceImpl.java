package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	private static final Logger LOGGER = Logger.getLogger(RegisterServiceImpl.class);

    private Map<String, ViewerService> viewerMap = new HashMap<String, ViewerService>();

	public RegisterServiceImpl() {
		LOGGER.debug("RegisterService const " + this);
	}

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
    }

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
        LOGGER.info("Actual list: " + viewerMap.keySet() +"\nAdd viewer for type: " + type);
		viewerMap.put(type, viewer);
	}

}
