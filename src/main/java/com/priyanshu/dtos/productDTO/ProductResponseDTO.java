package com.priyanshu.dtos.productDTO;

import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import com.priyanshu.dtos.categoryDTO.CategoryResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private Boolean isActive;
    private String brand;
    private CategoryResponseDTO category;

    private List<ProductImageResponseDTO> productImages;
}
