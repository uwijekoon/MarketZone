package com.ci6225.marketzone.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.ci6225.marketzone.model.Cart;
import com.ci6225.marketzone.model.CartItem;
import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.service.CartService;
import com.ci6225.marketzone.service.ProductService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@RequestMapping(value = {"/addItem"}, method = RequestMethod.POST)
	public String addItem(HttpServletRequest request, @ModelAttribute("cartForm") CartItem item,
			BindingResult bindingResult, ModelMap model, Errors errors) { 
		Product product = productService.findById(item.getProduct().getId());
		cartService.getCartFromSession(request).addItemsToCart(product, item.getQuantity());;
		return ViewConstants.INDEX;
	}
	
	@RequestMapping(value = {"/viewCart"}, method = RequestMethod.GET)
	public String viewCart(HttpServletRequest request) {
		Cart cart = cartService.getCartFromSession(request);
		return ViewConstants.INDEX;
	}

}
