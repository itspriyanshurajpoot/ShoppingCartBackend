package com.priyanshu.service.productImage;

import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import com.priyanshu.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IProductImageService {

    // add product image
    List<ProductImageResponseDTO> addProductImage(Long productId, List<MultipartFile> imageFile) throws IOException, SQLException;


    // delete product image by id
    void deleteProductImage(Long productImageId);


    // get product image by id
    ProductImage getProductImageById(Long productImageId);
}
