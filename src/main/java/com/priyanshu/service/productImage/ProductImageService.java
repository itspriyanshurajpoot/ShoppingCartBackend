package com.priyanshu.service.productImage;

import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import com.priyanshu.entity.Product;
import com.priyanshu.entity.ProductImage;
import com.priyanshu.exceptions.ResourceNotFoundException;
import com.priyanshu.repository.ProductImageRepository;
import com.priyanshu.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductImageService implements IProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductImageResponseDTO> addProductImage(Long productId, List<MultipartFile> imageFile) throws IOException, SQLException {

        // Check if the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        for(MultipartFile file: imageFile) {

            // Validate the file type and size if necessary
            if (file.isEmpty() || file.getSize() == 0) {
                throw new IllegalArgumentException("Invalid file: " + file.getOriginalFilename());
            }

            // Logic to save the image file and create a ProductImage entity
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageName(file.getOriginalFilename());
            productImage.setImageType(file.getContentType());
            productImage.setImage(new SerialBlob(file.getBytes()));
            ProductImage savedProduct = productImageRepository.save(productImage);

            savedProduct.setImageUrl("/api/v2/product-image/get-image/" + savedProduct.getProductImageId());
            productImageRepository.save(savedProduct);
        }


        // Convert the saved ProductImage to ProductImageResponseDTO
        List<ProductImage> productImages = productImageRepository.findByProduct(product);
        return productImages.stream()
                .map(this::productImageToResponseDto)
                .toList();
    }

    @Override
    public void deleteProductImage(Long productImageId) {
        // Check if the product image exists
        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Product image not found with id: " + productImageId));


        // Logic to delete product image by id
        productImageRepository.delete(productImage);
    }

    @Override
    public ProductImage getProductImageById(Long productImageId) {

        // Logic to get product image by id
        ProductImage productImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Product image not found with id: " + productImageId));


        return productImage;
    }


    // Additional methods can be added as needed
    private ProductImageResponseDTO productImageToResponseDto(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageResponseDTO.class);
    }
}
