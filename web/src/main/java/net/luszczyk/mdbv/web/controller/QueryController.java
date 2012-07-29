package net.luszczyk.mdbv.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luszczyk.mdbv.common.Table;
import net.luszczyk.mdbv.common.service.FileService;
import net.luszczyk.mdbv.common.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/query")
public class QueryController {

	@Autowired
	private QueryService queryService;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView moView = new ModelAndView("query");
		moView.addObject("tabele", new Table(null, null));

		return moView;

	}

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public ModelAndView run(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String query = request.getParameter("query");
		ModelAndView moView = new ModelAndView("query");
		Table table = queryService.runQuery(query).getTable();

		moView.addObject("h", generateHeaderMap("Query result"));
		moView.addObject("tabele", table);

		/*
		 * moView.addObject("script",
		 * "\\<script type=\\\"text/javascript\\\"> jQuery(document).ready(" +
		 * "function() { "+ "pixDisplay.initialize();" +
		 * "pixDisplay.assignLinks();" + "} ); \\</script\\>");
		 */

		return moView;

	}

	@RequestMapping(value = "/${path}/domain", method = RequestMethod.POST)
	public void getDocumentFileContentPost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = request.getParameter("path");
		String mimeType = fileService.getFileType(fileName);

		byte[] content = fileService.getFileContent(fileName);
		response.setContentType(mimeType);
		response.setContentLength(content.length);
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + '"');

		response.getOutputStream().write(content);
		response.getOutputStream().flush();
	}

	@RequestMapping(value = "/domain/{fileName}/fileContent", method = RequestMethod.GET)
	public void getDocumentFileContent(final HttpServletResponse response,
			@PathVariable("fileName") final String fileName) throws IOException {

		String mimeType = fileService.getFileType(fileName);

		byte[] content = fileService.getFileContent(fileName);
		response.setContentType(mimeType);
		response.setContentLength(content.length);
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + '"');

		response.getOutputStream().write(content);
		response.getOutputStream().flush();
	}

	private Map<String, Object> generateHeaderMap(String title) {
		Map<String, Object> map = generateHeaderMap();
		map.put("title", title);
		return map;
	}

	private Map<String, Object> generateHeaderMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Home");
		map.put("js", new String[] { "jquery.min.js", "pixDisplay.js" });

		return map;
	}

}