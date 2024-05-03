package com.productservices.productservices.Services;

import org.springframework.web.bind.annotation.GetMapping;

public interface CategoryServices {

	String getAllCatogories();

	String getProductsInCatogory();

}