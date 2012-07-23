package net.luszczyk.mdbv.common.service.impl;

import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.service.ViewerService;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {

	private static final Logger logger = Logger
			.getLogger(BeanPostProcessorImpl.class);

	private RegisterService registerService;

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {

		logger.debug("Process bean: " + arg0);

		if (implementsInterface(arg0, ViewerService.class)) {
			ViewerService viewer = (ViewerService) arg0;
			for (String s : viewer.getTypeList()) {
				registerService.addTypeAndViewer(s, viewer);
			}
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	public static Boolean implementsInterface(Object object, Class interf) {
		for (Class c : object.getClass().getInterfaces()) {
			if (c.equals(interf)) {
				return true;
			}
		}
		return false;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

}
