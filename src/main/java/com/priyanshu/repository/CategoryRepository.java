package com.priyanshu.repository;

import com.priyanshu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods can be defined here if needed
    Optional<Category> findByNameIgnoreCase(String name);
}
