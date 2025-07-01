package com.priyanshu.service.product;

import com.priyanshu.dtos.productDTO.ProductRequestDTO;
import com.priyanshu.dtos.productDTO.ProductResponseDTO;
import com.priyanshu.entity.Category;
import com.priyanshu.entity.Product;
import com.priyanshu.exceptions.ResourceNotFoundException;
import com.priyanshu.repository.CategoryRepository;
import com.priyanshu.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        // Convert ProductRequestDTO to Product entity
        Product product = requestDtoToProduct(productRequestDTO);


        // check if category exists
        Category category = categoryRepository.findByNameIgnoreCase(productRequestDTO.getCategory())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(productRequestDTO.getCategory());
                    newCategory.setDescription(productRequestDTO.getCategory() + " description");
                    return categoryRepository.save(newCategory);
                });

        // Set the category to the product
        product.setCategory(category);

        // Save the product to the database
        Product savedProduct = productRepository.save(product);
        return productToResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        // Check if the product exists
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // check the category exists
        Category category = categoryRepository.findByNameIgnoreCase(productRequestDTO.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));


        // Update the product details
        existingProduct.setName(productRequestDTO.getName());
        existingProduct.setDescription(productRequestDTO.getDescription());
        existingProduct.setPrice(productRequestDTO.getPrice());
        existingProduct.setStockQuantity(productRequestDTO.getStockQuantity());
        existingProduct.setIsActive(productRequestDTO.getIsActive());
        existingProduct.setBrand(productRequestDTO.getBrand());
        existingProduct.setCategory(category);
        // Save the updated product to the database
        Product updatedProduct = productRepository.save(existingProduct);
        return productToResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Product not found");
                        });
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(this::productToResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            return products.stream()
                    .map(this::productToResponseDto)
                    .toList();
        }
        throw new ResourceNotFoundException("No products found");
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return productRepository.findByCategory(category)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByName(String productName) {
        return productRepository.findByName(productName)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByPriceRange(double minPrice, double maxPrice) {

        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByNameAndCategory(String productName, String categoryName) {
        return productRepository.findByNameAndCategory(
                        productName,
                        categoryRepository.findByNameIgnoreCase(categoryName)
                                .orElseThrow(() -> new ResourceNotFoundException("Category not found")))
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByBrand(String brandName) {

        return productRepository.findByBrand(brandName)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByBrandAndCategory(String brandName, String categoryName) {
        return productRepository.findByCategoryAndBrand(
                        categoryRepository.findByNameIgnoreCase(categoryName)
                                .orElseThrow(() -> new ResourceNotFoundException("Category not found")),
                        brandName)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> getProductsByNameAndBrand(String productName, String brandName) {
        return productRepository.findByNameAndBrand(productName, brandName)
                .stream()
                .map(this::productToResponseDto)
                .toList();
    }


    // product request to product
    Product requestDtoToProduct(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }


    // product to product response dto
    ProductResponseDTO productToResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

}
