package net.luszczyk.mdbv.web.controller;

import javax.servlet.http.HttpSession;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.util.Message;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/db")
public class DataBaseController {
	
	private static final Logger LOG = Logger.getLogger(IndexController.class);

	@Autowired
	private DatabaseConnectionService databaseConnectionService;

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody
	Message handleDBTest(@RequestBody DataBasePostgres dataBasePostgres)
			throws Exception {

		Message message = new Message();
		try {
			databaseConnectionService.test(dataBasePostgres);
			message.setStatus(0);
			message.setMsg("Database " + dataBasePostgres.getDbName()
					+ " available.");
		} catch (Exception e) {
			message.setStatus(1);
			message.setMsg("Error connetion database: " + e.getMessage());
		}

		return message;
	}

	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public @ResponseBody
	Message handleDBConnect(@RequestBody DataBasePostgres dataBasePostgres, HttpSession session)
			throws Exception {

		Message message = new Message();
		try {
			databaseConnectionService.connect(dataBasePostgres);
			session.setAttribute("db", dataBasePostgres);
			message.setStatus(0);
			message.setMsg("Connected to " + dataBasePostgres.getDbName());
		} catch (Exception e) {
			message.setStatus(1);
			message.setMsg("Error connetion database: " + e.getMessage());
			LOG.error("Error connecting db" ,e);
		}

		return message;
	}
	
	@ModelAttribute(value="db")
	DataBase createDataBase() {
		return new DataBasePostgres();
	}

}