package net.luszczyk.mdbv.common.service;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewerService {
	
	protected static final List<String> typeList = new ArrayList<String>();

	public List<String> getTypeList() {
		return typeList;
	}
	
	public abstract String getLink(final String fileName);

}
