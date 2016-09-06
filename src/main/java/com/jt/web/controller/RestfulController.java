package com.jt.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.jt.web.conf.ApplicationProperties;
import com.jt.web.model.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class RestfulController {
	
	@Autowired
	private ServletContext context;

	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}
	
	@RequestMapping(value = "/version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String version() {
		return new Gson().toJson(ApplicationProperties.get("project.version"));
	}

	@RequestMapping(value = "/sample/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String sampleJson() {
		Data data = new Data();
		data.data = "Hello World";
		data.numberData = 10;
		data.numberList = new LinkedList<Integer>();
		data.numberList.add(1);
		data.numberList.add(2);
		data.numberList.add(3);
		data.numberList.add(4);
		data.numberList.add(5);
		data.numberList.add(6);
		data.numberList.add(7);
		data.mapData = new HashMap<String, String>();
		data.mapData.put("Name", "Andy Lim");
		data.mapData.put("Address", "N/A");
		data.mapData.put("Gamer Tag", "arcthefallen");
		data.mapData.put("Current Date", "12/10/2014");

		return new Gson().toJson(data);
	}
	
	@RequestMapping(value = "/sample/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String sampleXml() throws IOException {
		String xml = "";

		InputStream inputStream = context
				.getResourceAsStream("/WEB-INF/sample/sample.xml");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		String line = "";
		while ((line = br.readLine()) != null) {
			xml += line + "\n";
		}

		return xml;
	}
	
	@RequestMapping(value = "/sample/docx", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
	@ResponseBody
	public String sampleDocx(HttpServletResponse response) throws IOException {
		String binaryData = "";

		InputStream inputStream = context
				.getResourceAsStream("/WEB-INF/sample/sample.docx");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		String line = "";
		while ((line = br.readLine()) != null) {
			binaryData += line + "\n";
		}

		response.setHeader("X-Content-Type-Options",
				"nosniff");
		return binaryData;
	}
	
	@RequestMapping(value = "/sample/docxDL", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] sampleDocxDL(HttpServletResponse response) throws IOException {
		InputStream inputStream = context
				.getResourceAsStream("/WEB-INF/sample/sample.docx");

		response.setHeader("Content-Disposition",
				"attachment; filename=sample.docx");
		
		return IOUtils.toByteArray(inputStream);
	}
	
	@RequestMapping(value = "/sample/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] samplePdf(HttpServletResponse response) throws IOException {
		InputStream inputStream = context
				.getResourceAsStream("/WEB-INF/sample/sample.pdf");
		
		response.setHeader("Content-Disposition",
				"inline; filename=sample.pdf");		
		return IOUtils.toByteArray(inputStream);
	}
}
