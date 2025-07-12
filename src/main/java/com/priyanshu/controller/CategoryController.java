package com.priyanshu.controller;


import com.priyanshu.dtos.categoryDTO.CategoryRequestDTO;
import com.priyanshu.dtos.categoryDTO.CategoryResponseDTO;
import com.priyanshu.response.ApiResponse;
import com.priyanshu.service.category.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
@Tag(name = "Category Controller")
public class CategoryController {

    private final ICategoryService categoryService;

    @Operation(summary = "Add Category")
    @PostMapping("/create-category")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryRequestDTO request) {

        ApiResponse response = new ApiResponse();
        response.setMessage("Category created successfully");
        response.setSuccess(true);
        response.setData(categoryService.createCategory(request));
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Get category by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        ApiResponse response = new ApiResponse();
        response.setMessage("Category retrieved successfully");
        response.setSuccess(true);
        response.setData(categoryService.getCategoryById(id));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All Category")
    public ResponseEntity<ApiResponse> getAllCategories() {
        ApiResponse response = new ApiResponse();
        response.setMessage("All categories retrieved successfully");
        response.setSuccess(true);
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        response.setData(categories);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update category by id")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO request) {
        ApiResponse response = new ApiResponse();
        response.setMessage("All categories retrieved successfully");
        response.setSuccess(true);
        response.setData(categoryService.updateCategory(id, request));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Category By id")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Category deleted successfully");
        response.setSuccess(true);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
