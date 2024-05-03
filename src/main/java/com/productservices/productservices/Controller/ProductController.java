package com.productservices.productservices.Controller;

import com.productservices.productservices.exceptions.NotFoundException;
import com.productservices.productservices.Services.ProductServices;

import com.productservices.productservices.dtos.ProductDto;
import com.productservices.productservices.models.Category;
import com.productservices.productservices.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
 public ProductServices productServices;


    //The Code for get All Product from
    @GetMapping()
    public List<Product> getAllProducts(){
        return productServices.getAllProducts();
    }


    //The Code for get Single Product from

     @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws Exception{

         MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
         headers.add(
                 "auth-token", "NoExcessForYou"
         );
         // optional is Common instance for empty().
         Optional<Product> productOptional= productServices.getSingleProduct(productId);
         if(productOptional.isEmpty()){
             throw new NotFoundException("No Product with product id : " +productId);

         }
         // If product is found, return it with OK status
         ResponseEntity<Product>response= new ResponseEntity<>( productOptional.get(),
                headers,
                HttpStatus.FOUND
         );
        return  response;
    }



    //The Code for to  Add new  Product
     @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product){
        Product newProduct=productServices.addNewProduct(product);
        ResponseEntity<Product>response=new ResponseEntity<>(newProduct,HttpStatus.OK);
        return response;
    }


@PatchMapping ("/{productId}")
    public  Product updateProduct(@PathVariable("productId") Long productId,
                   @RequestBody ProductDto productDto
      ){
        Product product=new Product();
        product.setDescription(productDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        return productServices.updateProduct(productId,product) ;
    }


    @DeleteMapping("/{productId}")

    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting a Product with id"+productId;
    }



    // this below code is handling the error message when we not find the product with product id  to make code
    // with clean responce else it will give error with white label and our error message
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception){
//        ErrorResponseDto errorResponseDto=new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//        return  new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
//
//    }
}
