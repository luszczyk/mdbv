package net.luszczyk.mdbv.web.controller;


import net.luszczyk.mdbv.web.utill.WebUtills;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView handleIndex() throws Exception {

		ModelAndView model = new ModelAndView("index");

		model.addObject("h", WebUtills.generateHeaderMap("Index"));

		return model;
	}

}