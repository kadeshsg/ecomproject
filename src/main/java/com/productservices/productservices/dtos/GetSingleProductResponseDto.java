package com.productservices.productservices.dtos;

import com.productservices.productservices.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;

}
