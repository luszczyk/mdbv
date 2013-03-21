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
		return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + fileName + "/view?iframe=true'>" + LINK_VAL + "</a>";
	}

    @Override
    public String getResourceView(final String domainId) {
        return "<img src=\"/web/content/domain/" + domainId + "/fileContent\"  alt=\"resource\"/>";
    }

    @Override
    public String getIcon() {
        return "<div style=\"width: 60px; height: 60px; background: url(/web/resources/images/files.gif) no-repeat -400px -379px;\"></div>";
    }
}
