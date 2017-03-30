package com.ci6225.marketzone.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.model.User;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private static final String VIEW_INDEX = "index";
	private static final String VIEW_REGISTER = "register";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"/register","/login"}, method = RequestMethod.GET)
	public String registerOnload(Map<String, Object> model) {  
        model.put("userForm", new User());
        model.put("countryList", getCountryList());
		return VIEW_REGISTER;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") @Validated User user, BindingResult bindingResult, ModelMap model) {
        
        if(bindingResult.hasErrors()) {
        	model.put("countryList", getCountryList());
        	return VIEW_REGISTER;
        } else {
        	return VIEW_INDEX;
        }
		
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userForm") User user, ModelMap model) {

		
		return VIEW_REGISTER;
	}
	
	
	protected Map<String, String> getCountryList() {
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");

		return country;
	}

}
