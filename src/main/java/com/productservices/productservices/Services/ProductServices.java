package com.productservices.productservices.Services;

import com.productservices.productservices.dtos.ProductDto;
import com.productservices.productservices.models.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductServices {

	List<Product> getAllProducts();

	Optional<Product> getSingleProduct(Long productId);

	Product addNewProduct( ProductDto product);

	Product updateProduct(Long productId, Product product);
	String replaceProduct(Long productId, Product product);

	boolean  deleteProduct(Long productId);

}