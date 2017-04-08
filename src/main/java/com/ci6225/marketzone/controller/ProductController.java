package com.ci6225.marketzone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ci6225.marketzone.model.OrderItem;
import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.ProductSearch;
import com.ci6225.marketzone.service.ProductService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/product")
@Scope("request")
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
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public String searchProducts(HttpServletRequest request, ModelMap model,  @ModelAttribute("searchForm") ProductSearch search, @RequestParam(value = "productName") String productName) { 
		List<Product> productList = productService.searchProducs(productName);
		request.setAttribute("availableProductList", productList);
        return ViewConstants.VIEW_INDEX;
	}


	@RequestMapping(value = {"/getProduct"}, method = RequestMethod.GET)
	public String getProductsDetails(HttpServletRequest request, ModelMap model, @RequestParam(value = "productId") int productId) { 
		Product product = productService.findById(productId);
		OrderItem item = new OrderItem();
		item.setQuantity(1);
		item.setProduct(product);
		model.put("cartItemForm", item);
        return ViewConstants.PRODUCT_DETAIL;
	}
	
}
