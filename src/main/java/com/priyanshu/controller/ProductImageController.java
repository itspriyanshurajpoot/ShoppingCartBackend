package com.priyanshu.controller;

import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import com.priyanshu.entity.ProductImage;
import com.priyanshu.response.ApiResponse;
import com.priyanshu.service.productImage.IProductImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product-image")
public class ProductImageController {

    private final IProductImageService productImageService;


    // add product image
    @PostMapping
    public ResponseEntity<ApiResponse> addProductImage(@RequestParam Long productId, @RequestBody List<MultipartFile> imageFile) throws SQLException, IOException {
        List<ProductImageResponseDTO> response = productImageService.addProductImage(productId, imageFile);

        ApiResponse apiResponse = ApiResponse.builder()
                .message("Product images added successfully")
                .success(true)
                .data(response)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    // delete product image by id
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProductImage(@RequestParam("product-id") Long productImageId) {
        productImageService.deleteProductImage(productImageId);

        ApiResponse apiResponse = ApiResponse.builder()
                .message("Product image deleted successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    // get product image by id
    @GetMapping("/get-image/{id}")
    public ResponseEntity<ByteArrayResource> getProductImageById(@PathVariable("id") Long productImageId) throws SQLException {
        ProductImage productImage = productImageService.getProductImageById(productImageId);

        ByteArrayResource resource = new ByteArrayResource(productImage.getImage().getBytes(1, (int) productImage.getImage().length()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productImage.getImageType()))
                .body(resource);


    }
}
