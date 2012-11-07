package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.table.Table;
import net.luszczyk.mdbv.web.utill.WebUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/query")
public class QueryController {

	@Autowired
	private QueryService queryService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView moView = new ModelAndView("query");
		moView.addObject("tabele", new Table(null, null, null));
        moView.addObject("query", "SELECT * FROM ...");

		return moView;
	}

    @RequestMapping(value = "/index/{tab}", method = RequestMethod.GET)
    public ModelAndView indexWithQueryPrefix(@PathVariable String tab) throws Exception {

        ModelAndView moView = new ModelAndView("query");
        moView.addObject("query", "SELECT * FROM " + tab);
        moView.addObject("tabele", new Table(null, null, null));

        return moView;
    }

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public ModelAndView run(HttpServletRequest request,
			HttpSession session) throws Exception {

		String query = request.getParameter("query");
		ModelAndView moView = new ModelAndView("query");
		Table table = queryService.runQuery(query).getTable();
        WebUtills.putFilesToSession(table, session);

		moView.addObject("h", WebUtills.generateHeaderMap("Query result"));
		moView.addObject("select", query);
		request.getSession().setAttribute("table", table);
		moView.addObject("tabele", table);

		return moView;
	}

}