package net.luszczyk.mdbv.modules.image.service;

import net.luszczyk.mdbv.common.service.ViewerService;
import org.springframework.stereotype.Service;

@Service
public class ViewerWktService extends ViewerService {

	{
		typeList.add("map/wkt");
	}

	@Override
	public String getLink(final String fileName) {
		return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + fileName + "/map?iframe=true&width=1024&height=768'>View Geometry on Map</a>" +
                "<br />" +
                "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + fileName + "/wkt?iframe=true&width=1024&height=768'>View Geometry on Blank Page</a>";
	}

    @Override
    public String getResourceView(final String domainId) {
        return "<img src=\"/web/content/domain/" + domainId + "/fileContent\"  alt=\"resource\"/>";
    }
}
