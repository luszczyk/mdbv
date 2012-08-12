package net.luszczyk.mdbv.modules.image.service;

import net.luszczyk.mdbv.common.service.ViewerService;

import org.springframework.stereotype.Service;

@Service
public class ViewerImageService extends ViewerService {

	{
		typeList.add("image/jpeg");
		typeList.add("image/png");
		typeList.add("image/gif");
	}

	@Override
	public String getLink(final String fileName) {
		return "<a href=\"/web/res/domain/" + fileName
				+ "/fileContent\" rel=\"pixDisplay\">view</a>";
	}

}
