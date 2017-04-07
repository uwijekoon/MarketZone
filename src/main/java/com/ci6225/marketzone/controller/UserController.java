package com.ci6225.marketzone.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String registerOnload(ModelMap model,HttpServletRequest request) {  
        model.put("userForm", new User());
        model.put("loginForm", new User());
        model.put("countryList", getCountryList());
		return VIEW_REGISTER;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, @ModelAttribute("userForm") @Validated User user, BindingResult bindingResult, ModelMap model, final RedirectAttributes redirectAttributes, Errors errors) {
        
		if(!bindingResult.hasErrors()){
			if(userService.findByUserCode(user.getUserCode()) != null) {
				errors.rejectValue("userCode", "exists.userForm.userCode", "UserName already exists!");
			}

			if(userService.findByUserEmail(user.getEmail()) != null) {
				errors.rejectValue("email", "exists.userForm.email", "Email already existes!");
			}
		}
		
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
        		return "redirect:/";
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", "required.userCode", "UserCode is required.");
		
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
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");

		return country;
	}

}
