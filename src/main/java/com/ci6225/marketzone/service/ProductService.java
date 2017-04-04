package com.ci6225.marketzone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ci6225.marketzone.dao.ProductDao;
import com.ci6225.marketzone.model.Product;

@Service("productService")
@Transactional
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Product findById(int id) {
		return productDao.findById(id);
	}
	
	public List<Product> getAvailableProductList() {
		return productDao.findAvailableProducts();
	}
	
	public void saveProduct(Product product) {
		productDao.persist(product);
	}
	
}
