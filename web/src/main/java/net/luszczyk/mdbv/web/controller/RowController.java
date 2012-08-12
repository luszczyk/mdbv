package net.luszczyk.mdbv.web.controller;

import javax.servlet.http.HttpServletRequest;

import net.luszczyk.mdbv.common.table.Entity;
import net.luszczyk.mdbv.common.table.Table;
import net.luszczyk.mdbv.web.utill.WebUtills;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/row")
public class RowController {

	@RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String id,
			HttpServletRequest request) throws Exception {

		Table tab = (Table) request.getSession().getAttribute("table");

		Entity entity = null;
		if (tab != null && id != null) {
			for (Entity e : tab.getEntities()) {
				if (e.getId().toString().equals(id)) {
					entity = e;
				}
			}
		}

		ModelAndView moView = new ModelAndView("row");
		moView.addObject("e", entity);
		moView.addObject("h", WebUtills.generateHeaderMap("Query result"));
		return moView;
	}
}
