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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.User;
import com.ci6225.marketzone.service.ProductService;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping("/seller")
public class SellerController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@RequestMapping(value = {"/productsList"}, method = RequestMethod.GET)
	public String getProductsList(HttpServletRequest request, ModelMap model) { 
		List<Product> productList = productService.getProductList((User)request.getSession().getAttribute("user"));
		request.setAttribute("productList", productList);
        return ViewConstants.SELLER_PRODUCT_LIST;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.GET)
	public String addProductOnLoad(ModelMap model) { 
		model.put("productForm", new Product());
        return ViewConstants.ADD_PRODUCT;
	}
	
	@RequestMapping(value = {"/addProduct"}, method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @ModelAttribute("productForm") @Validated Product product, BindingResult bindingResult, ModelMap model, 
			Errors errors, final RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
	         logger.error("Validation");
	         model.put("productForm", new Product());
	         return ViewConstants.ADD_PRODUCT;
	      } else {
	    	  if(productService.saveProduct(product,  (User)request.getSession().getAttribute("user"))) {
	    		  redirectAttributes.addFlashAttribute("success", "Product added successfully!");
	    		  return "redirect:/";
	    	  } else {
	    		  redirectAttributes.addFlashAttribute("error", "Product added successfully!");
	    		  return "redirect:/";
	    	  }
	      }
	}

	
}
