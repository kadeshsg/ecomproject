package com.productservices.productservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
public class Product extends BaseMode {  // this is the product model that same
    // as fakestore Api model to access them

    private String title;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
    private String imageUrl;

}