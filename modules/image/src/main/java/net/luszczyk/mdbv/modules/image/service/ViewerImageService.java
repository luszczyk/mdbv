package net.luszczyk.mdbv.modules.image.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.luszczyk.mdbv.common.service.ViewerService;

@Service
public class ViewerImageService implements ViewerService {

	private static final List<String> typeList = new ArrayList<String>();

	{
		typeList.add("image/jpeg");
		typeList.add("image/png");
		typeList.add("image/gif");
	}

	@Override
	public List<String> getTypeList() {
		return typeList;
	}

	@Override
	public String getLink(final String fileName) {
		return "<a href=\"domain/" + fileName
				+ "/fileContent\" rel=\"pixDisplay\">view</a>";
	}

}
