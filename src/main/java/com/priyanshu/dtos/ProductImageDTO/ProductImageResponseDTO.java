package com.priyanshu.dtos.ProductImageDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageResponseDTO {
    private Long productImageId;
    private String imageUrl;
    private String imageName;
    private String imageType;
}
