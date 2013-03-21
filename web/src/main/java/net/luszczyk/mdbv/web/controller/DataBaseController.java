package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.model.AvailableDataBase;
import net.luszczyk.mdbv.common.model.DataBase;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import net.luszczyk.mdbv.common.service.DatabaseConnectionHolder;
import net.luszczyk.mdbv.common.util.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/db")
public class DataBaseController {

    private static final Logger LOG = Logger.getLogger(IndexController.class);

    @Autowired
    private DatabaseConnectionHolder databaseConnectionHolder;

    @RequestMapping(value = "/types/details/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> handleDBAvailableDetails(@PathVariable String name)
            throws Exception {

        AvailableDataBase availableDataBase = AvailableDataBase.valueOf(name);
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("port", availableDataBase.getPort());
        map.put("name", availableDataBase.getDefaultDb());
        return map;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public
    @ResponseBody
    Message handleDBTest(@RequestBody DataBasePostgres dataBasePostgres)
            throws Exception {

        Message message = new Message();
        try {
            databaseConnectionHolder.test(dataBasePostgres);
            message.setStatus(0);
            message.setMsg("Database " + dataBasePostgres.getDataBaseDTO().getName()
                    + " available.");
        } catch (Exception e) {
            message.setStatus(1);
            message.setMsg("Error connetion database: " + e.getMessage());
        }

        return message;
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public
    @ResponseBody
    Message handleDBConnect(@RequestBody DataBaseDTO dataBase, HttpSession session) {

        Message message = new Message();
        try {

            if (dataBase.getType() == null) {
                dataBase.setType(AvailableDataBase.POSTGRES);
            }
            databaseConnectionHolder.connect(dataBase);
            session.setAttribute("db", dataBase);
            message.setStatus(0);
            message.setMsg("Connected to " + dataBase.getName());
        } catch (Exception e) {
            message.setStatus(1);
            message.setMsg("Error conneting database: " + e.getMessage());
            LOG.error("Error connecting db", e);
        }

        return message;
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public
    @ResponseBody
    Message handleDBDisconnect(HttpSession session) {

        Message message = new Message();
        try {

            databaseConnectionHolder.disconnect();
            session.removeAttribute("db");
            message.setStatus(0);
            message.setMsg("Disconnected");
        } catch (Exception e) {
            message.setStatus(1);
            message.setMsg("Error disconneting database: " + e.getMessage());
            LOG.error("Error disconnecting db", e);
        }

        return message;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView dbDetails(HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {


        List<String> dbs = null;
        Boolean dbHasSchemas = null;
        try {
            dbs = databaseConnectionHolder.getAllDbs();
            dbHasSchemas = databaseConnectionHolder.getConnectionDetails().isDbSchemaAvailable();
        } catch (DatabaseConnectionException e) {
            LOG.error("Error connecting with database");
            session.setAttribute("db", null);
            String contextPath = request.getContextPath();
            return new ModelAndView(response.encodeRedirectURL(contextPath + "/index"));
        }
        ModelAndView modelAndView = new ModelAndView("db/details");
        modelAndView.addObject("dbs", dbs);
        modelAndView.addObject("dbHasSchemas", dbHasSchemas);

        return modelAndView;
    }

    @RequestMapping(value = "/details/all-schemas/{dbName}", method = RequestMethod.POST)
    @ResponseBody
    public Message getAllSchemas(@PathVariable String dbName, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {

        Message message = new Message();
        try {
            DataBase dataBase = databaseConnectionHolder.getConnectionDetails();
            DataBaseDTO dataBaseDTO = dataBase.getDataBaseDTO();
            dataBaseDTO.setName(dbName);
            try {
                databaseConnectionHolder.connect(dataBaseDTO);
            } catch (Exception e) {
                LOG.error("Error connecting with database");
                response.sendRedirect("/web/index");
            }
            List<String> schemas = databaseConnectionHolder.getAllSchemas();
            message.setData(schemas);
            session.setAttribute("db", dataBaseDTO);
            message.setStatus(0);
            message.setMsg("Connected to " + dataBase.getDataBaseDTO().getName());
        } catch (DatabaseConnectionException e) {
            LOG.error("Error connecting with database");
            session.setAttribute("db", null);
            String contextPath = request.getContextPath();
            response.setHeader("location", response.encodeRedirectURL(contextPath + "/index"));
        }

        return message;
    }

    @RequestMapping(value = "/details/all-tables/{schema}", method = RequestMethod.POST)
    @ResponseBody
    public Message getAllTables(@PathVariable String schema, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {

        Assert.notNull(schema);
        Message message = new Message();
        List<String> tables = null;
        try {
            tables = databaseConnectionHolder.getAllTablesForSchema(schema);
        } catch (DatabaseConnectionException e) {
            LOG.error("Error connecting with database");
            session.setAttribute("db", null);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/index") );
        }

        Map<String, Object> tab = new HashMap<String, Object>();
        tab.put("table", tables);
        if(databaseConnectionHolder.getConnectionDetails().isDbSchemaAvailable()) {
            tab.put("schema", schema);
        } else {
            DataBase dataBase = databaseConnectionHolder.getConnectionDetails();
            DataBaseDTO dataBaseDTO = dataBase.getDataBaseDTO();
            dataBaseDTO.setName(schema);
            try {
                databaseConnectionHolder.connect(dataBaseDTO);
            } catch (Exception e) {
                LOG.error("Error connecting with database");
                response.sendRedirect("/web/index");
            }
        }
        message.setData(tab);
        message.setStatus(0);
        return message;
    }

    @ModelAttribute(value = "db")
    DataBaseDTO createDataBase() {
        return new DataBaseDTO();
    }

}