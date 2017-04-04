package com.ci6225.marketzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.dao.OrderDao;
import com.ci6225.marketzone.model.Order;
import com.ci6225.marketzone.util.PDFGenerator;

@Service("orderService")
@Transactional
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	PDFGenerator pdfGen;
	
	public Order findById(int id) {
		return orderDao.findById(id);
	}
	
	public String generatePdf(String rootUrl) throws Exception{
		return pdfGen.generateOrderPdf(rootUrl);
	}
}
