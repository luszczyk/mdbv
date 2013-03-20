package net.luszczyk.mdbv.modules.audio.service;

import net.luszczyk.mdbv.common.service.ViewerService;
import org.springframework.stereotype.Service;

@Service
public class ViewerAudioService extends ViewerService {

	{
		typeList.add("audio/mp3");
	}

    @Override
    public String getLink(final String domainId) {
        return "<a rel=\"prettyPhoto[iframe]\" href='/web/content/" + domainId + "/view?iframe=true&width=400&height=100'>" + LINK_VAL + "</a>";
    }

    @Override
    public String getResourceView(final String domainId) {

        return "<div style=\"padding-top: 20px;\">" +
                "<object type=\"application/x-shockwave-flash\" data=\"/web/resources/swf/player_mp3_maxi.swf\" width=\"350\" height=\"50\">\n" +
                "\t<param name=\"movie\" value=\"/web/resources/swf/player_mp3_maxi.swf\" />\n" +
                "\t<param name=\"FlashVars\" value=\"mp3=/web/content/domain/" + domainId + "/fileContent&amp;showstop=1&amp;showvolume=1\" />\n" +
                "</object>" +
                "</div>";
    }
}
