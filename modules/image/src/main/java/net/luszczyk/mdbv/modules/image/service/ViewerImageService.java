package net.luszczyk.mdbv.modules.image.service;

import net.luszczyk.mdbv.common.service.ViewerService;
import org.springframework.stereotype.Service;

@Service
public class ViewerImageService extends ViewerService {

	{
		typeList.add("image/jpeg");
        typeList.add("image/jpg");
		typeList.add("image/png");
		typeList.add("image/gif");
	}

	@Override
	public String getLink(final String fileName) {
		return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + fileName + "/view?iframe=true&width=1024&height=768'>" + LINK_VAL + "</a>";
	}

    @Override
    public String getResourceView(final String domainId) {
        return "<img src=\"/web/content/domain/" + domainId + "/fileContent\"  alt=\"resource\"/>";
    }
}
