package net.luszczyk.mdbv.web.controller;

import net.luszczyk.mdbv.common.service.QueryService;
import net.luszczyk.mdbv.common.service.RegisterService;
import net.luszczyk.mdbv.common.table.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/content")
public class ResourceController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/domain/{domainId}/fileContent", method = RequestMethod.GET)
    public void getDocumentFileContent(final HttpServletResponse response, HttpSession session,
                                       @PathVariable("domainId") final String domainId) throws IOException {

        Domain domain = (Domain) session.getAttribute(domainId);

        byte[] content = queryService.getContentByte(domain, null);
        response.setContentType(domain.getMimeType());
        response.setContentLength(content.length);
        response.addHeader("Content-Disposition", "attachment; filename=\""
                + domain.getContent() + '"');

        response.getOutputStream().write(content);
        response.getOutputStream().flush();
    }

    @RequestMapping(value = "/{domainId}/view", method = RequestMethod.GET)
    public ModelAndView viewContent(final HttpSession session,
                            @PathVariable("domainId") final String domainId) throws IOException {

        ModelAndView model = new ModelAndView("content");

        Domain domain = (Domain) session.getAttribute(domainId);

        session.setAttribute("res", registerService.getViewerService(domain.getMimeType()).getResourceView(domain.getId().toString()) );

        return model;
    }

}
