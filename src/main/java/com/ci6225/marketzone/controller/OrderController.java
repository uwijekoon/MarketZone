package com.ci6225.marketzone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.service.OrderService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@RequestMapping(value = {"/downloadPdf"}, method = RequestMethod.GET)
	public void addItem(HttpServletRequest request, @ModelAttribute("cartForm") OrderItem item, HttpServletResponse response,
			BindingResult bindingResult, ModelMap model, Errors errors) { 
		try{
			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			String root = url.substring( 0, url.indexOf(uri) ) + request.getContextPath();
			File pdf = new File(orderService.generatePdf(root));
		InputStream inputStream = new FileInputStream(pdf);
		IOUtils.copy(inputStream, response.getOutputStream());
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + pdf.getName() +"\""));
		 
	      response.flushBuffer();
		}catch(Exception e){
			e.printStackTrace();
		}
		//return ViewConstants.INDEX;
	}

}
