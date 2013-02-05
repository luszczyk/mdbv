package net.luszczyk.mdbv.modules.audio.service;

import net.luszczyk.mdbv.common.service.ViewerService;
import org.springframework.stereotype.Service;

@Service
public class ViewerAudioService extends ViewerService {

	{
		typeList.add("audio/ogg");
	}

    @Override
    public String getLink(final String domainId) {
        return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + domainId + "/view?iframe=true&width=1024&height=768'>view</a>";
    }

    @Override
    public String getResourceView(final String domainId) {

        return "<audio  controls='controls'>" +
                "<source src='/web/content/domain/" + domainId + "/fileContent' type='audio/ogg'>" +
                "</audio>";
    }
}
