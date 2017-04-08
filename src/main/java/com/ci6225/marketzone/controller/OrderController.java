package com.ci6225.marketzone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.OrderService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/order")
@Scope("request")
public class OrderController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	@RequestMapping(value = {"/downloadPdf"}, method = RequestMethod.GET)
	public void downloadPdf(HttpServletRequest request, @ModelAttribute("cartForm") OrderItem item, HttpServletResponse response,
			BindingResult bindingResult, ModelMap model, Errors errors) { 
		try{
			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			String root = url.substring( 0, url.indexOf(uri) ) + request.getContextPath();
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			Order order = orderService.findById(orderId);
			File pdf = new File(orderService.generatePdf(root, order));
			response.setHeader("Content-Disposition", String.format("attachment; filename=\" MarketZone_order_" + order.getId()+ ".pdf\""));
			InputStream inputStream = new FileInputStream(pdf);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.setContentType("application/pdf");

			response.flushBuffer();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@RequestMapping(value = {"/viewOrderHistory"}, method = RequestMethod.GET)
	public String viewOrderHistory(HttpServletRequest request) { 
		try{
			User user = (User)(request.getSession().getAttribute("user"));
			List<Order> orderList = orderService.getOrderList(user);
			request.setAttribute("orderList", orderList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ViewConstants.VIEW_ORDER_HISTORY;
	}

	@RequestMapping(value = {"/viewOrder"}, method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request) { 
		try{
			int id = Integer.parseInt(request.getParameter("orderId"));
			Order order = orderService.findById(id);
			request.setAttribute("order", order);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ViewConstants.VIEW_ORDER;
	}
	
	@RequestMapping(value = {"/viewOrderRequests"}, method = RequestMethod.GET)
	public String viewOrderRequests(HttpServletRequest request) { 
		try{
			User user = (User)(request.getSession().getAttribute("user"));
			List<Order> orderList = orderService.getSellerOrderList(user.getSeller());
			request.setAttribute("orderList", orderList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ViewConstants.VIEW_ORDER_REQUESTS;
	}
	
	@RequestMapping(value = {"/viewOrderRequest"}, method = RequestMethod.GET)
	public String viewOrderRequest(HttpServletRequest request) { 
		try{
			int id = Integer.parseInt(request.getParameter("orderId"));
			User user = (User)request.getSession().getAttribute("user");
			int sellerId = user.getId();
			Order order = orderService.getOrderRequest(id, sellerId);
			request.setAttribute("order", order);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ViewConstants.VIEW_ORDER_REQUEST;
	}
	

	@RequestMapping(value = {"/downloadOrderRequestPdf"}, method = RequestMethod.GET)
	public void downloadOrderRequestPdf(HttpServletRequest request, @ModelAttribute("cartForm") OrderItem item, HttpServletResponse response,
			BindingResult bindingResult, ModelMap model, Errors errors) { 
		try{
			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			String root = url.substring( 0, url.indexOf(uri) ) + request.getContextPath();
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			User user = (User)request.getSession().getAttribute("user");
			Order order = orderService.getOrderRequest(orderId, user.getId());
			File pdf = new File(orderService.generatePdf(root, order));
			response.setHeader("Content-Disposition", String.format("attachment; filename=\" MarketZone_order_" + order.getId()+ ".pdf\""));
			InputStream inputStream = new FileInputStream(pdf);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.setContentType("application/pdf");

			response.flushBuffer();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
