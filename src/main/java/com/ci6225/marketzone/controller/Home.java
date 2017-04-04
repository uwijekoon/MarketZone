package com.ci6225.marketzone.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class Home {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Home.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return ViewConstants.INDEX;

	}

}
