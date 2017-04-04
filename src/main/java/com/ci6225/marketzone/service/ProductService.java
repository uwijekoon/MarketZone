package com.ci6225.marketzone.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.marketzone.dao.ProductDao;
import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.Seller;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.util.CommonUtil;

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
	
	public List<Product> getProductList(User seller) {
		return productDao.findProducts(seller.getId());
	}

	public boolean saveProduct(Product product, User seller) {

		String imagePath ="/Users/asankalakmal/Desktop/MarketZoneImages";
		try {
			MultipartFile multipartFile = product.getImageFile();
			product.setImage(null);
			if(multipartFile != null) {
				String fileName = multipartFile.getOriginalFilename();
				String newName = CommonUtil.imageNameGenerate(fileName);
				String fullUploadPath = imagePath + File.separator + seller.getId();
				if(CommonUtil.isSetDirectory(fullUploadPath)) {
					FileCopyUtils.copy(product.getImageFile().getBytes(), new File(fullUploadPath + File.separator + newName));
					product.setImage(newName);
				} 
			}
			Seller subSeller = new Seller();
			subSeller.setId(seller.getId());
			product.setSeller(subSeller);
			product.setCreatedBy(seller.getId());
			product.setUpdatedBy(seller.getId());
			product.setCreateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			product.setUpdateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			product.setDeleted('F');
			
			productDao.persist(product);
			return true;
			
		} catch (IOException ex) {
			return false;
		}
		
	}

}
