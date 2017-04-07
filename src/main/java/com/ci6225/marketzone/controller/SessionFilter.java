package com.ci6225.marketzone.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionFilter  extends HandlerInterceptorAdapter{

		private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

		//before the actual handler will be executed
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
System.out.println("Session filter --------------------------------");
			HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        HttpSession session = req.getSession(false);
	        String loginURI = req.getContextPath() + "/user/login";

	        String signUpURI = req.getContextPath() + "/user/register";
	        String cartURI1 = req.getContextPath() + "/cart/addItem";
	        String cartURI2 = req.getContextPath() + "/cart/viewCart";
	        String cartURI3 = req.getContextPath() + "/cart/updateCart";
	        String cartURI4 = req.getContextPath() + "/cart/checkout";

	        boolean loggedIn = session != null && session.getAttribute("user") != null;
	        boolean loginRequest = req.getRequestURI().equals(loginURI);
	        boolean signupRequest = req.getRequestURI().equals(signUpURI);
	        boolean cartRequest = req.getRequestURI().equals(cartURI1) || req.getRequestURI().equals(cartURI2) || req.getRequestURI().equals(cartURI3) || 
	        		(req.getRequestURI().equals(cartURI4) && "GET".equalsIgnoreCase(request.getMethod()));
	        
	        boolean filter = req.getRequestURI().equals(req.getContextPath())
	                || req.getRequestURI().equals(req.getContextPath() + "/");
	        boolean resourceFile = req.getRequestURI().toLowerCase().endsWith("js") || req.getRequestURI().toLowerCase().endsWith("css")
	                || req.getRequestURI().toLowerCase().endsWith("png") || req.getRequestURI().toLowerCase().endsWith("jpg")
	                || req.getRequestURI().toLowerCase().endsWith("jpeg");

	        if (loggedIn || loginRequest || signupRequest || filter || resourceFile || cartRequest) {
	            return true;
	        } else {
	            res.sendRedirect(req.getContextPath() + "/register");
	            return false;
	        }

		}
}

