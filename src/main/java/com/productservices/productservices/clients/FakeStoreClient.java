package com.productservices.productservices.clients;

import com.productservices.productservices.dtos.ProductDto;
import com.productservices.productservices.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    public RestTemplateBuilder restTemplateBuilder;

    public  FakeStoreClient(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder=restTemplateBuilder;
    }


    public  List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                //this below list class will , this class will add the list of products
                FakeStoreProductDto[].class
        );
        return Arrays.asList(l.getBody());
    }

    public  Optional<FakeStoreProductDto> getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId
        );
        return Optional.of(response.getBody());

    }

    public  FakeStoreProductDto addNewProduct( ProductDto product){

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto>response=restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        return response.getBody();
    }

    FakeStoreProductDto updateProduct(Long productId, Product product){
        return null;
    }
    FakeStoreProductDto replaceProduct(Long productId, Product product){
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
