package net.luszczyk.mdbv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.web.utill.WebUtills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public @ResponseBody
	String handleDBTest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			DataBase dataBase = new DataBasePostgres(
					request.getParameter("host"), request.getParameter("user"),
					request.getParameter("pass"), Integer.parseInt(request
							.getParameter("port")), "mdbvdb");

			databaseConnectionService.test(dataBase);
		} catch (Exception e) {
			throw new Exception("Database connection error !" + e.getMessage());
		}

		return "Database connection success !";
	}

}