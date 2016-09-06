package com.jt.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jt.web.conf.ApplicationProperties;

import com.google.gson.Gson;

@Controller
@RequestMapping("/version")
public class VersionController {
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String version() {		
		return new Gson().toJson(ApplicationProperties.get("project.version"));
	}
}
