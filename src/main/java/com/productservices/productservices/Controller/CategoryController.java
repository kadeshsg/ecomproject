package com.productservices.productservices.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @GetMapping()

    public  String  getAllCatogories(){
        return  " Getting all categories";
    }

    public  String getProductsInCatogory(){
        return "Get Products in catogery";
    }
}
