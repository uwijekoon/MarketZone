package com.ci6225.marketzone.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ci6225.marketzone.model.Product;
import com.ci6225.marketzone.model.User;

@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Product name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitPrice", "required.unitPrice", "Unit Price is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "required.quantity", "Quantity is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Description is required.");
		
		if(product.getQuantity() < 0) {
			errors.rejectValue("quantity", "quantity.minAmount", "Please enter valid quantity");
		}
		if(product.getUnitPrice() < 0) {
			errors.rejectValue("unitPrice", "unitPrice.minAmount", "Please enter valid unitPrice");
		}
		
	}

}
