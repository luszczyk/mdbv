package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionService;
import net.luszczyk.mdbv.common.util.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
			message.setMsg("Error conneting database: " + e.getMessage());
			LOG.error("Error connecting db" ,e);
		}

		return message;
	}

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView dbDetails()
            throws Exception {


        List<String> dbs = databaseConnectionService.getAllDbs();

        ModelAndView modelAndView = new ModelAndView("db/details");

        modelAndView.addObject("dbs", dbs);

        return modelAndView;
    }

    @RequestMapping(value = "/details/all-schemas/{dbName}", method = RequestMethod.POST)
    @ResponseBody
    public Message getAllSchemas(@PathVariable String dbName, HttpSession session)
            throws Exception {

        Message message = new Message();
        try {

            DataBase dataBase = databaseConnectionService.getConnectionDetails();
            dataBase.setDbName(dbName);
            databaseConnectionService.connect(dataBase);
            List<String> schemas = databaseConnectionService.getAllSchemas();
            message.setData(schemas);
            session.setAttribute("db", dataBase);
            message.setStatus(0);
            message.setMsg("Connected to " + dataBase.getDbName());
        } catch (Exception e) {
            message.setStatus(1);
            message.setMsg("Error conneting database: " + dbName + "\nError message:" + e.getLocalizedMessage());
            LOG.error("Error connecting db", e);
        }

        return message;
    }

    @RequestMapping(value = "/details/all-tables/{schema}", method = RequestMethod.POST)
    @ResponseBody
    public Message getAllTables(@PathVariable String schema)
            throws Exception {

        Assert.notNull(schema);
        Message message = new Message();
        List<String> schemas = databaseConnectionService.getAllTablesForSchema(schema);
        message.setData(schemas);
        message.setStatus(0);
        return message;
    }

    @ModelAttribute(value="db")
	DataBase createDataBase() {
		return new DataBasePostgres();
	}

}