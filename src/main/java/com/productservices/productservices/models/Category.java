package com.productservices.productservices.models;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
@Entity
public class Category extends BaseMode {
    private  String name;
    private String  description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
