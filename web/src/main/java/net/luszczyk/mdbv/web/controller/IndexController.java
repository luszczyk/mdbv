package net.luszczyk.mdbv.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView handleIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("query");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Home");
		map.put("js", new String[] { "jquery.min.js", "pixDisplay.js" });

		model.addObject("h", map);

		model.addObject("msg", "Index");

		return model;
	}

}