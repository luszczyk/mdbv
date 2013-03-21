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
        return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + domainId + "/view?iframe=true&width=1024&height=768'>" + LINK_VAL + "</a>";
    }

    @Override
    public String getResourceView(final String domainId) {

        return "<video  controls='controls'>" +
                "<source src='/web/content/domain/" + domainId + "/fileContent' type='video/ogg'>" +
                "</video>";
    }

    @Override
    public String getIcon() {
        return "<div style=\"width: 60px; height: 60px; background: url(/web/resources/images/files.gif) no-repeat -495px -560px;\"></div>";
    }
}
