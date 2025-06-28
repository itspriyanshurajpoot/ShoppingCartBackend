package com.priyanshu.repository;

import com.priyanshu.entity.Category;
import com.priyanshu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods can be defined here if needed
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByBrand(String brandName);

    List<Product> findByNameAndCategory(String name, Category category);

    List<Product> findByNameAndBrand(String name, String brandName);


    List<Product> findByCategoryAndBrand(Category category, String brandName);


}
