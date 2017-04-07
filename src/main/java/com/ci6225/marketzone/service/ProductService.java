package com.ci6225.marketzone.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.marketzone.dao.ProductDao;
import com.ci6225.marketzone.dao.SellerDao;
import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.Seller;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.util.CommonUtil;
import com.ci6225.marketzone.util.Properties;

@Service("productService")
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SellerDao sellerDao;

	public Product findById(int id) {
		return productDao.findById(id);
	}

	public List<Product> getAvailableProductList() {
		return productDao.findAvailableProducts();
	}
	
	public List<Product> searchProducs(String productName) {
		return productDao.searchProducts(productName);
	}
	

	public List<Product> getProductList(User seller) {
		return sellerDao.getProductList(seller.getId());
	}

	public boolean saveProduct(Product product, User seller) {

		String imagePath =Properties.getProperty("FILE_STORE_PATH")+"images";
		try {
			MultipartFile multipartFile = product.getImageFile();
			product.setImage(null);
			String fileName = multipartFile.getOriginalFilename();
			if(multipartFile != null && !StringUtils.isEmpty(fileName)) {
				String newName = CommonUtil.imageNameGenerate(fileName);
				String fullUploadPath = imagePath + File.separator + seller.getId();
				if(CommonUtil.isSetDirectory(imagePath) && CommonUtil.isSetDirectory(fullUploadPath)) {
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
	
	public boolean updateProduct(Product product, int sellerId) {
		
		Product exProduct = findById(product.getId());
		if(exProduct != null && exProduct.getSeller().getId()==sellerId) {
			exProduct.setName(product.getName());
			exProduct.setUnitPrice(product.getUnitPrice());
			exProduct.setQuantity(product.getQuantity());
			exProduct.setDescription(product.getDescription());
			productDao.persist(exProduct);
			return true;
		}
		
		return false;
	}
	
	public boolean updateQuantity(int productId, int usedQuantity){
		Product product = findById(productId);
		if(product != null) {
			product.setQuantity(product.getQuantity() - usedQuantity);
			productDao.persist(product);
			return true;
		}
		
		return false;
	}

}
