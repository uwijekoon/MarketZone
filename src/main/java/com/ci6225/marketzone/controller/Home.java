package com.ci6225.marketzone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.ProductSearch;
import com.ci6225.marketzone.service.ProductService;
import com.ci6225.marketzone.util.Properties;
import com.ci6225.marketzone.util.ViewConstants;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class Home {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Home.class);
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Autowired
    ServletContext context; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, ModelMap model) {
		List<Product> productList = productService.getAvailableProductList();
		request.setAttribute("availableProductList", productList);
		model.put("searchForm", new ProductSearch());
        return ViewConstants.VIEW_INDEX;

	}
	
	@RequestMapping(value = "image/{userId}/{imageName}.{ext}", method = RequestMethod.GET)
	public void image(@PathVariable(value = "userId") int userId, @PathVariable(value = "imageName") String imageName, @PathVariable(value = "ext") String ext, HttpServletResponse response ) throws IOException {
		String imagePath = Properties.getProperty("FILE_STORE_PATH")+"images"+File.separator + userId + File.separator +imageName+"."+ext;
		InputStream in;
		if(new File(imagePath).exists()){
			in = new FileInputStream(imagePath);
		}else{
			in = context.getResource("themes/images/no_image.png").openStream();
		}
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}

}
