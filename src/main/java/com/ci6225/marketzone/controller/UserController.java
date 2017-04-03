package com.ci6225.marketzone.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.model.Seller;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String VIEW_INDEX = "index";
	private static final String VIEW_REGISTER = "register";
	private static final String VIEW_BUYER = "redirect:/product/getProductsList";
	private static final String VIEW_SELLER = "productList";
	private static final int USER_TYPE_BUYER = 1;
	private static final int USER_TYPE_SELLER = 2;
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"/register","/login"}, method = RequestMethod.GET)
	public String registerOnload(ModelMap model) {  
        model.put("userForm", new User());
        model.put("loginForm", new User());
        model.put("countryList", getCountryList());
		return VIEW_REGISTER;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") @Validated User user, BindingResult bindingResult, ModelMap model) {
        
        if(bindingResult.hasErrors()) {
        	model.put("countryList", getCountryList());
        	model.put("userForm", user);
            model.put("loginForm", new User());
        	return VIEW_REGISTER;
        } else {
        	try{
        		userService.userRegister(user);
        		logger.info("User register successfully! userCode:"+user.getUserCode());
        	} catch(Exception e) {
        		logger.error("User Reigister error happen :"+ e);
        		return VIEW_REGISTER;
        	}
        	
        	return VIEW_INDEX;
        }
		
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute("loginForm") User user, BindingResult bindingResult, ModelMap model, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", "required.userCode", "UserCode is required.");
		
		if(bindingResult.hasErrors()) {
        	//Set errors
        } else {
        	User dbUser = userService.userLogin(user.getUserCode(), user.getPassword());
        	
        	if(dbUser != null) {
        		request.setAttribute("user", dbUser);
	        	if(USER_TYPE_BUYER == dbUser.getUserType()) {
	        		return VIEW_BUYER;
	        	} else if(USER_TYPE_SELLER == dbUser.getUserType()) {
	        		return VIEW_SELLER;
	        	}
        	} 
        }
		
		model.put("countryList", getCountryList());
    	model.put("userForm", new User());
        model.put("loginForm", user);
        
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
