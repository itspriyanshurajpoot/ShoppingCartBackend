package com.priyanshu.repository;

import com.priyanshu.entity.Product;
import com.priyanshu.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // Additional query methods can be defined here if needed
    List<ProductImage> findByProduct(Product product);
    List<ProductImage> findByImageUrlContaining(String keyword);

}
