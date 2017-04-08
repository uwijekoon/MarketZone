package com.ci6225.marketzone.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.dao.OrderDao;
import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.model.Seller;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.util.PDFGenerator;

@Service("orderService")
@Transactional
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	PDFGenerator pdfGen;
	
	public Order findById(int id) {
		return orderDao.findById(id);
	}
	
	public String generatePdf(String rootUrl, Order order) throws Exception{
		return pdfGen.generateOrderPdf(rootUrl, order);
	}
	
	public void saveCart(Order order, User user){
		order.setOrderDate(new Timestamp(new Date().getTime()));
		order.setUser(user);
		for(OrderItem item:order.getOrderItems()){
			item.setOrder(order);
			item.setAmount(item.getProduct().getUnitPrice() * item.getQuantity());
		}
		orderDao.saveCart(order);
		for(OrderItem item:order.getOrderItems()){
			productService.updateQuantity(item.getProduct().getId(), item.getQuantity());
		}
	}
	
	public List<Order> getOrderList(User user) {
		return orderDao.findOrders(user);
	}
	
	public List<Order> getSellerOrderList(Seller seller) {
		return orderDao.findOrders(seller);
	}
	
	public Order getOrderRequest(int id, int sellerId) {
		return orderDao.getOrderRequest(id, sellerId);
	}
}
