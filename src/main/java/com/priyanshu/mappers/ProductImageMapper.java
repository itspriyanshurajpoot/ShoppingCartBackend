package com.priyanshu.mappers;

import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import com.priyanshu.entity.ProductImage;

import java.util.List;

public class ProductImageMapper {

    public static List<ProductImageResponseDTO> toDto(List<ProductImage> images){
        return images.stream()
                .map(image -> ProductImageResponseDTO.builder()
                        .productImageId(image.getProductImageId())
                        .imageUrl(image.getImageUrl())
                        .build())
                .toList();
    }
}
