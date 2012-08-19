package net.luszczyk.mdbv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.web.utill.WebUtills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@Autowired
	private DatabaseConnectionService databaseConnectionService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView handleIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("index");

		model.addObject("h", WebUtills.generateHeaderMap("Index"));

		return model;
	}

	@RequestMapping(value = "/index/dbtest", method = RequestMethod.POST)
	public @ResponseBody DataBasePostgres handleDBTest(@RequestBody DataBasePostgres dataBasePostgres)
			throws Exception {

		DataBasePostgres dataBasePostgres2 = new DataBasePostgres();
		try {
			
			databaseConnectionService.test(dataBasePostgres);
		} catch (Exception e) {
			dataBasePostgres2.setName("ok");
		}
		
		 
		return dataBasePostgres2;
	}

}