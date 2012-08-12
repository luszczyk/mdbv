package net.luszczyk.mdbv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luszczyk.mdbv.web.utill.WebUtills;

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

		model.addObject("h", WebUtills.generateHeaderMap("Index"));

		model.addObject("msg", "Index");

		return model;
	}

}