package net.luszczyk.mdbv.modules.video.service;

import net.luszczyk.mdbv.common.service.ViewerService;
import org.springframework.stereotype.Service;

@Service
public class ViewerVideoService extends ViewerService {

	{
		typeList.add("video/ogg");
	}

    @Override
    public String getLink(final String domainId) {
        return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + domainId + "/view?iframe=true&width=1024&height=768'>view</a>";
    }

    @Override
    public String getResourceView(final String domainId) {

        return "<video  controls='controls'>" +
                "<source src='/web/content/domain/" + domainId + "/fileContent' type='video/ogg'>" +
                "</video>";
    }
}
