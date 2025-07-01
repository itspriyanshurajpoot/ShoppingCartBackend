package com.priyanshu.controller;

import com.priyanshu.dtos.productDTO.ProductRequestDTO;
import com.priyanshu.dtos.productDTO.ProductResponseDTO;
import com.priyanshu.response.ApiResponse;
import com.priyanshu.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;


    // create product
    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequestDTO requestDTO){

        ApiResponse response = ApiResponse.builder()
                .data(productService.createProduct(requestDTO))
                .message("Product created successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // update product
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductRequestDTO requestDTO, @RequestParam Long productId){

        ApiResponse response = ApiResponse.builder()
                .data(productService.updateProduct(productId, requestDTO))
                .message("Product updated successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){

        productService.deleteProduct(productId);

        ApiResponse response = ApiResponse.builder()
                .message("Product deleted successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }



    // get product by id
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){

        ProductResponseDTO product = productService.getProductById(productId);

        ApiResponse response = ApiResponse.builder()
                .data(product)
                .message("Product retrieved successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // get all products
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getAllProducts())
                .message("All products retrieved successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // get products by category
    @GetMapping("/by-category")
    public ResponseEntity<ApiResponse> getProductsByCategory(@RequestParam String categoryName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByCategory(categoryName))
                .message("Products retrieved by category successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }



    // get products by name
    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse> getProductsByName(@RequestParam String productName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByName(productName))
                .message("Products retrieved by name successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }



    // get products by price range
    @GetMapping("/by-price-range")
    public ResponseEntity<ApiResponse> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice){
        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByPriceRange(minPrice, maxPrice))
                .message("Products retrieved by price range successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }



    // get products by brand
    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brandName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByBrand(brandName))
                .message("Products retrieved by brand successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }



    // get products by name and category
    @GetMapping("/by-name-and-category")
    public ResponseEntity<ApiResponse> getProductsByNameAndCategory(@RequestParam String productName, @RequestParam String categoryName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByNameAndCategory(productName, categoryName))
                .message("Products retrieved by name and category successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // get products by brand and category
    @GetMapping("/by-brand-and-category")
    public ResponseEntity<ApiResponse> getProductsByBrandAndCategory(@RequestParam String brandName, @RequestParam String categoryName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByBrandAndCategory(brandName, categoryName))
                .message("Products retrieved by brand and category successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    // get products by name and brand
    @GetMapping("/by-name-and-brand")
    public ResponseEntity<ApiResponse> getProductsByNameAndBrand(@RequestParam String productName, @RequestParam String brandName){

        ApiResponse response = ApiResponse.builder()
                .data(productService.getProductsByNameAndBrand(productName, brandName))
                .message("Products retrieved by name and brand successfully")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }
}
