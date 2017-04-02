package com.ci6225.marketzone.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ci6225.marketzone.service.CartService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	@Qualifier("cartService")
	private CartService cartService;
	
	@RequestMapping(value = {"/AddItem"}, method = RequestMethod.POST)
	public String addItem(ModelMap model) {  
        return ViewConstants.INDEX;
	}

}
