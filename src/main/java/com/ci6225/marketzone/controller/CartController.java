package com.ci6225.marketzone.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.model.OrderItem;
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
	public String viewCart(HttpServletRequest request, ModelMap model) {
		Cart cart = cartService.getCartFromSession(request);
		request.setAttribute("cart", cart);
		return ViewConstants.VIEW_CART;
	}
	
	@RequestMapping(value = {"/updateCart"}, method = RequestMethod.GET)
	public String updateCart(HttpServletRequest request, ModelMap model) {
		Cart cart = cartService.getCartFromSession(request);
		String[] quantityListStr = request.getParameter("quantityList").split(",");
        List<Integer> quantityList = new ArrayList<>();
        for (String quantity : quantityListStr) {
            String[] split = quantity.split("-");
            int index = Integer.valueOf(split[0]);
            int itemQuantity = Integer.valueOf(split[1]);
            quantityList.add(index, itemQuantity);
        }
        cartService.updateCartItems(cart, quantityList);
		
		
		request.setAttribute("cart", cart);
		return ViewConstants.VIEW_CART;
	}
	
	@RequestMapping(value = {"/checkout"}, method = RequestMethod.POST)
	public String checkout(HttpServletRequest request, ModelMap model) {
		Cart cart = cartService.getCartFromSession(request);
        cartService.removeCartFromSession(request);
		return ViewConstants.VIEW_CART;
	}
	
	@RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
	public String checkoutLoad(HttpServletRequest request, ModelMap model) {
		Cart cart = cartService.getCartFromSession(request);
		Order order = new Order();
		order.setOrderItems(cart.getItemList());
		model.put("orderForm", order);
	
        return ViewConstants.VIEW_CHECKOUTT;
	}

}
