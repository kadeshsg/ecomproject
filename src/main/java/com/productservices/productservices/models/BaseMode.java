package com.productservices.productservices.models;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import  java.util.*;

@Getter
@Setter
@MappedSuperclass
public class BaseMode {  // initial base model the first model
    @Id
    private  Long id;
    private Date CreatedAt;
    private  Date lastUpdatedAt;
    private boolean isDeleted;


}
