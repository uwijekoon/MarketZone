package com.ci6225.marketzone.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.model.Cart;
import com.ci6225.marketzone.model.OrderItem;

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

	public void updateCartItems(Cart cart, List<Integer> quantityList) {
		int index = 0;
		for (OrderItem item : cart.getItemList()) {
			item.setQuantity(quantityList.get(index));
			item.setAmount(item.getQuantity() * item.getProduct().getUnitPrice());
			index++;
		}
	}

}
