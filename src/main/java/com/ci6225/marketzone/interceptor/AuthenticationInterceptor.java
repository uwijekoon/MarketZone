package com.ci6225.marketzone.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("-------------AuthenticationInterceptor: Session Intercepter");
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        String loginURI = req.getContextPath() + "/user/login";
        String signUpURI = req.getContextPath() + "/user/register";
        String cartURI1 = req.getContextPath() + "/cart/addItem";
        String cartURI2 = req.getContextPath() + "/cart/viewCart";
        String cartURI3 = req.getContextPath() + "/cart/updateCart";
        String cartURI4 = req.getContextPath() + "/cart/checkout";
        String cartURI5 = req.getContextPath() + "/product/getProduct";
        String cartURI6 = req.getContextPath() + "/product/search";
       

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean signupRequest = req.getRequestURI().equals(signUpURI);
        boolean cartRequest = req.getRequestURI().equals(cartURI1) || req.getRequestURI().equals(cartURI2) || req.getRequestURI().equals(cartURI3) || req.getRequestURI().contains(cartURI5) 
        		|| req.getRequestURI().contains(cartURI6) || (req.getRequestURI().equals(cartURI4) && "GET".equalsIgnoreCase(request.getMethod()));
        
        boolean filter = req.getRequestURI().equals(req.getContextPath())
                || req.getRequestURI().equals(req.getContextPath() + "/");
        boolean resourceFile = req.getRequestURI().toLowerCase().endsWith("js") || req.getRequestURI().toLowerCase().endsWith("css")
                || req.getRequestURI().toLowerCase().endsWith("png") || req.getRequestURI().toLowerCase().endsWith("jpg")
                || req.getRequestURI().toLowerCase().endsWith("jpeg");

        if (loggedIn || loginRequest || signupRequest || filter || resourceFile || cartRequest) {
        	logger.info("-------------AuthenticationInterceptor: Allowed");
            return true;
        } else {
        	logger.info("-------------AuthenticationInterceptor: Not Allowed");
            res.sendRedirect(signUpURI);
            return false;
        }

	}
}
