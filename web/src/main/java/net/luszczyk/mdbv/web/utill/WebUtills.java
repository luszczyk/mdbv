package net.luszczyk.mdbv.web.utill;

import net.luszczyk.mdbv.common.table.Domain;
import net.luszczyk.mdbv.common.table.Entity;
import net.luszczyk.mdbv.common.table.Table;

import javax.servlet.http.HttpSession;
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
        return map;
    }

    public static void putFilesToSession(Table table, HttpSession session) {

        for (Entity e : table.getEntities()) {

            for (Domain d : e.getValues()) {
                    session.setAttribute(d.getId().toString(), d);
            }

        }

    }

}
