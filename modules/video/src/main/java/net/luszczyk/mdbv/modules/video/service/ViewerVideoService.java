package net.luszczyk.mdbv.modules.video.service;

import net.luszczyk.mdbv.common.service.ViewerService;

import org.springframework.stereotype.Service;

@Service
public class ViewerVideoService extends ViewerService {

	{
		typeList.add("video/ogg");
	}

    @Override
    public String getLink(final String fileName) {
        return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/view/" + fileName + "?iframe=true&width=800&height=600'>view</a>";
    }

    @Override
    public String getResourceView(final String fileName) {

        return "<video  controls='controls'>" +
                "<source src='/web/content/domain/" + fileName + "/fileContent' type='video/ogg'>" +
                "</video>";
    }
}
