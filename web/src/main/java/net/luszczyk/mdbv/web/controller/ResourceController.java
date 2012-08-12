package net.luszczyk.mdbv.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luszczyk.mdbv.common.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/res")
public class ResourceController {
	
	@Autowired
	private FileService fileService;

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

}
