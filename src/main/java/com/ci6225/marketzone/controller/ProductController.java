package com.ci6225.marketzone.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.model.Product;
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
        return ViewConstants.VIEW_INDEX;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.GET)
	public String addProductOnLoad(ModelMap model) { 
		model.put("productForm", new Product());
        return ViewConstants.ADD_PRODUCT;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @ModelAttribute("productForm") Product product, BindingResult bindingResult, ModelMap model, Errors errors, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) { 

        return ViewConstants.ADD_PRODUCT;
	}
	

	@RequestMapping(value = {"/getProduct"}, method = RequestMethod.GET)
	public String getProductsDetails(HttpServletRequest request, ModelMap model) { 
		Product product = productService.findById(Integer.valueOf(request.getParameter("productId")));
		OrderItem item = new OrderItem();
		item.setProduct(product);
		model.put("cartItemForm", item);
        return ViewConstants.PRODUCT_DETAIL;
	}
	
}
