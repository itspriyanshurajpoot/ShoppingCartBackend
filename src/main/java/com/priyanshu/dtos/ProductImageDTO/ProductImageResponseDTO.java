package com.priyanshu.dtos.ProductImageDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductImageResponseDTO {
    private Long productImageId;
    private String imageUrl;
}
