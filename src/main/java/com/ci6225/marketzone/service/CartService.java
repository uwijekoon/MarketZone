package com.ci6225.marketzone.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.model.Cart;

@Service("cartService")
@Transactional
public class CartService {
	
	public Cart getCartFromSession(HttpServletRequest request){
		if(request.getSession().getAttribute("cart") == null){
			request.getSession().setAttribute("cart",new Cart());
		}
		return (Cart) request.getSession().getAttribute("cart");
	}
	 public void removeCartFromSession(HttpServletRequest request){
		 request.getSession().removeAttribute("cart");
	 }
	 
	
	
}
