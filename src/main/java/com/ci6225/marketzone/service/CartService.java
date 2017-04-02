package com.ci6225.marketzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.dao.CartDao;
import com.ci6225.marketzone.model.Cart;

@Service("cartService")
@Transactional
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	
	public Cart findById(int id) {
		return cartDao.findById(id);
	}
	
	
}
