package net.luszczyk.mdbv.web.utill;

import java.util.HashMap;
import java.util.Map;

public class WebUtills {
	
	public static Map<String, Object> generateHeaderMap(String title) {
		Map<String, Object> map = generateHeaderMap();
		map.put("title", title);
		return map;
	}

	public static Map<String, Object> generateHeaderMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Home");
		map.put("js", new String[] { "pixDisplay.js" });

		return map;
	}

}
