package net.luszczyk.mdbv.common.service;

import java.util.List;

public interface ViewerService {
	
	public List<String> getTypeList();
	public String getLink(final String fileName);

}
