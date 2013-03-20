package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.table.Table;
import net.luszczyk.mdbv.common.util.Message;
import net.luszczyk.mdbv.web.utill.WebUtills;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping(value = "/query")
public class QueryController {

    private static final Logger LOG = Logger.getLogger(QueryController.class);

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView moView = new ModelAndView("query");
        moView.addObject("table", new Table(null, null, null));
        moView.addObject("select", " * FROM ...");

        return moView;
    }

    @RequestMapping(value = "/index/{tab}", method = RequestMethod.GET)
    public ModelAndView indexWithQueryPrefix(@PathVariable String tab) {

        ModelAndView moView = new ModelAndView("query");
        moView.addObject("select", " * FROM " + tab);
        moView.addObject("table", new Table(null, null, null));

        return moView;
    }

    @RequestMapping(value = "/index/{schema}/{tab}", method = RequestMethod.GET)
    public ModelAndView indexWithQueryPrefixWithSchema(@PathVariable String schema, @PathVariable String tab) {

        ModelAndView moView = new ModelAndView("query");
        moView.addObject("select", " * FROM " +schema + "." + tab);
        moView.addObject("table", new Table(null, null, null));

        return moView;
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public
    @ResponseBody
    Message run(HttpServletRequest request, HttpServletResponse response,
                HttpSession session, @RequestBody Map<String, String> queryMap) throws IOException {

        String query = "SELECT " + queryMap.get("query");
        if (!query.toUpperCase().trim().startsWith("SELECT")) {
             return new Message(500, "You can use only select query !");
        } else if (query.contains(";")) {
            String [] queryStrings = query.split(";");
            query = queryStrings[0];
        }
        Table table = null;

        try {
            table = queryService.runQuery(query).getTable();
        } catch (DatabaseConnectionException e) {
            LOG.error("Error connecting with database");
            session.setAttribute("db", null);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/index") );
        } catch (SQLException e) {
            return new Message(500, "Error running query: " + e.getMessage());
        }
        WebUtills.putFilesToSession(table, session);


        request.getSession().setAttribute("select", query);
        request.getSession().setAttribute("table", table);

        return new Message(200, "Query run success");
    }

    @RequestMapping(value = "/show/result", method = RequestMethod.GET)
    public ModelAndView showResult(HttpServletRequest request) {

        ModelAndView moView = new ModelAndView("query");

        moView.addObject("h", WebUtills.generateHeaderMap("Query result"));
        moView.addObject("select", request.getSession().getAttribute("select"));
        moView.addObject("table", request.getSession().getAttribute("table"));

        return moView;
    }

}