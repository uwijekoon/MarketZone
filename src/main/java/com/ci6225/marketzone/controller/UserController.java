package com.ci6225.marketzone.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.UserService;
import com.ci6225.marketzone.validator.FormValidation;

@Controller
@RequestMapping("/user")
@Scope("request")
public class UserController {
	
	private static final String VIEW_REGISTER = "register";
	private static final int USER_TYPE_BUYER = 1;
	private static final int USER_TYPE_SELLER = 2;
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	private FormValidation formValidator;
	
	@RequestMapping(value = {"/register","/login"}, method = RequestMethod.GET)
	public String registerOnload(ModelMap model,HttpServletRequest request) {  
        model.put("userForm", new User());
        model.put("loginForm", new User());
        model.put("countryList", getCountryList());
		return VIEW_REGISTER;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, @ModelAttribute("userForm") @Validated User user, BindingResult bindingResult, ModelMap model, final RedirectAttributes redirectAttributes, Errors errors) {
        
		formValidator.registerValidate(user, errors, bindingResult);
        if(bindingResult.hasErrors()) {
        	model.put("countryList", getCountryList());
        	model.put("userForm", user);
            model.put("loginForm", new User());
        	return VIEW_REGISTER;
        } else {

        	try{
        		userService.userRegister(user);
        		redirectAttributes.addFlashAttribute("flashSuccess", "User added successfully!");
        		logger.info("User register successfully! userCode:"+user.getUserCode());
        		return "redirect:/user/register";
        	} catch(Exception e) {
        		logger.error("User Reigister error happen :"+ e);
        		request.setAttribute("errorMessage", "Unknown Error!");
        	}
        	
        	model.put("countryList", getCountryList());
        	model.put("userForm", user);
        	model.put("loginForm", new User());
    		return VIEW_REGISTER;
        }
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute("loginForm") User user, BindingResult bindingResult, ModelMap model, Errors errors) {

		formValidator.loginValidate(user, errors);
		if(!bindingResult.hasErrors()) {
        	User dbUser = userService.userLogin(user.getUserCode(), user.getPassword());
        	if(dbUser != null) {
        		request.getSession().setAttribute("user", dbUser);
	        	if(USER_TYPE_BUYER == dbUser.getUserType()) {
	        		String redirectPage = request.getParameter("redirectPage");
	        		if(redirectPage != null){
	        			return "redirect:/"+redirectPage;
	        		}
	        		return "redirect:/";
	        	} else if(USER_TYPE_SELLER == dbUser.getUserType()) {
	        		return "redirect:/seller/productList";
	        	}
        	} else {
        		request.setAttribute("errorMessage", "Invalid UserName or Password!");
        	}
        }
		
		model.put("countryList", getCountryList());
    	model.put("userForm", new User());
        model.put("loginForm", user);
        
		return VIEW_REGISTER;
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String userLogout(HttpServletRequest request, ModelMap model) {  
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	protected Map<String, String> getCountryList() {
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("United Stated", "United Stated");
		country.put("China", "China");
		country.put("Singapore", "Singapore");
		country.put("Malaysia", "Malaysia");

		return country;
	}

}
