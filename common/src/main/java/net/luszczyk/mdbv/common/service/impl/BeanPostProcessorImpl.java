package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {

	private static final Logger LOGGER = Logger
			.getLogger(BeanPostProcessorImpl.class);

	private RegisterService registerService;
	
	public BeanPostProcessorImpl() {
		LOGGER.debug("BeanPostProcessorImpl construct ");
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {

		//logger.debug("Process bean: " + arg0+ " \n is Viewer: " + (arg0 instanceof ViewerService));

		if (arg0 instanceof ViewerService) {
			ViewerService viewer = (ViewerService) arg0;
			for (String s : viewer.getTypeList()) {
				registerService.addTypeAndViewer(s, viewer);
				LOGGER.info("Add viewer for type: " + s);
			}
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

}
