package com.priyanshu.dtos.productDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDTO {
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private Boolean isActive;
    private String category; // Assuming categoryId is used to link to a Category entity
}
