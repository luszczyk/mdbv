package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.exception.DatabaseConnectionException;
import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Domain;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/content")
public class ResourceController {

    private static final Logger LOG = Logger.getLogger(ResourceController.class);

    @Autowired
    private QueryService queryService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/domain/{domainId}/fileContent", method = RequestMethod.GET)
    public void getDocumentFileContent(final HttpServletResponse response, HttpSession session, HttpServletRequest request,
                                       @PathVariable("domainId") final String domainId) throws IOException, SQLException {

        Domain domain = (Domain) session.getAttribute(domainId);

        byte[] content = new byte[0];
        try {
            content = queryService.getContentByte(domain, null);
        } catch (DatabaseConnectionException e) {
            LOG.error("Error connecting with database");
            session.setAttribute("db", null);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/index"));
        } catch (SQLException e) {
            LOG.error("Error getting content bytes ", e);
            throw e;
        }

        response.setContentType(domain.getMimeType());
        response.setContentLength(content.length);
        response.addHeader("Content-Disposition", "attachment; filename=\""
                + domain.getContent() + '"');

        response.getOutputStream().write(content);
        response.getOutputStream().flush();
    }

    @RequestMapping(value = "/{domainId}/map", method = RequestMethod.GET)
    public ModelAndView viewMap(final HttpSession session,
                                    @PathVariable("domainId") final String domainId) throws IOException {

        ModelAndView model = new ModelAndView("map");

        Domain domain = (Domain) session.getAttribute(domainId);

        session.setAttribute("res", domain.getContent());

        return model;
    }

    @RequestMapping(value = "/{domainId}/view", method = RequestMethod.GET)
    public ModelAndView viewContent(final HttpSession session,
                                    @PathVariable("domainId") final String domainId) throws IOException {

        ModelAndView model = new ModelAndView("content");

        Domain domain = (Domain) session.getAttribute(domainId);

        session.setAttribute("res", registerService.getViewerService(domain.getMimeType()).getResourceView(domain.getId().toString()));

        return model;
    }

}
