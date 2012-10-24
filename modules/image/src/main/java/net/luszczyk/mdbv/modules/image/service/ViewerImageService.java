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
		return "<a rel=\"prettyPhoto[iframe]\" href='/web/res/view/" + fileName + "?iframe=true&width=800&height=600'>view</a>";
	}

    @Override
    public String getResourceView(final String fileName) {
        return "<img src=\"/web/res/domain/" + fileName + "/fileContent\"  alt=\"resource\"/>";
    }
}
