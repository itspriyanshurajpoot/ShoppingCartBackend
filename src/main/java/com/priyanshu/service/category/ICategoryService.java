package com.priyanshu.service.category;

import com.priyanshu.dtos.categoryDTO.CategoryRequestDTO;
import com.priyanshu.dtos.categoryDTO.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {

    // Method to create a new category
    CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO);


    // Method to get a category by its ID
    CategoryResponseDTO getCategoryById(Long id);


    // Method to update an existing category by its ID
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO);


    // Method to delete a category by its ID
    void deleteCategory(Long id);



    // Method to get all categories
    List<CategoryResponseDTO> getAllCategories();
}
