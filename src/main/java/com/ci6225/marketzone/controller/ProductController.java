package com.ci6225.marketzone.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.ProductService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@RequestMapping(value = {"/getProductsList"}, method = RequestMethod.GET)
	public String getProductsList(HttpServletRequest request, ModelMap model) { 
		List<Product> productList = productService.getAvailableProductList();
		request.setAttribute("availableProductList", productList);
        return ViewConstants.INDEX;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.GET)
	public String addProductOnLoad(ModelMap model) { 
		model.put("productForm", new Product());
        return ViewConstants.ADD_PRODUCT;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @ModelAttribute("productForm") @Validated Product product, BindingResult bindingResult, ModelMap model, 
			Errors errors, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
		
		String imagePath ="/Users/asankalakmal/Desktop/MarketZoneImages";
		
		if (bindingResult.hasErrors()) {
	         logger.error("Validation");
	         model.put("productForm", new Product());
	         return ViewConstants.ADD_PRODUCT;
	      } else {            
	         
	    	  try {
	    		  MultipartFile multipartFile = product.getImageFile();
	 	         String uploadPath = imagePath + File.separator + File.separator;
	 	         //Now do something with file...
	 	         FileCopyUtils.copy(product.getImageFile().getBytes(), new File(uploadPath + product.getImageFile().getOriginalFilename()));
	 	         String fileName = multipartFile.getOriginalFilename();
	 	         model.addAttribute("fileName", fileName);
	 	         logger.info("Uploaded image name:"+fileName);
	 	         product.setImage(fileName);
	 	         productService.saveProduct(product);
	    		  
	    	  } catch(IOException ex) {
	    		  logger.error("Product image upload failed:"+ex);
	    	  }
	    	  
	    	  return "redirect:/";
	         
	      }
		
		
        //return ViewConstants.ADD_PRODUCT;
	}

	@RequestMapping(value = {"/getProduct"}, method = RequestMethod.GET)
	public String getProductsDetails(HttpServletRequest request, ModelMap model) { 
		Product product = productService.findById(Integer.valueOf(request.getParameter("productId")));
		request.setAttribute("detailProduct", product);
        return ViewConstants.PRODUCT_DETAIL;
	}
	
}
