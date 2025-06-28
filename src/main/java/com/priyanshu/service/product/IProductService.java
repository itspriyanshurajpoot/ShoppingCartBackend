package com.priyanshu.service.product;

import com.priyanshu.dtos.productDTO.ProductRequestDTO;
import com.priyanshu.dtos.productDTO.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    // Create a new product
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    // Update an existing product
    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);


    // Delete a product by ID
    void deleteProduct(Long productId);


    // Get a product by ID
    ProductResponseDTO getProductById(Long productId);


    // Get all products
    List<ProductResponseDTO> getAllProducts();


    // Get products by category
   List< ProductResponseDTO> getProductsByCategory(String categoryName);


    // Get products by name
    List<ProductResponseDTO> getProductsByName(String productName);


    // Get products by price range
    List<ProductResponseDTO> getProductsByPriceRange(double minPrice, double maxPrice);


    // Get Products by name and category
    List<ProductResponseDTO> getProductsByNameAndCategory(String productName, String categoryName);

    // Get products by brand
    List<ProductResponseDTO> getProductsByBrand(String brandName);

    // Get Products by brand and category
    List<ProductResponseDTO> getProductsByBrandAndCategory(String brandName, String categoryName);


    // Get products by name and brand
    List<ProductResponseDTO> getProductsByNameAndBrand(String productName, String brandName);
}
