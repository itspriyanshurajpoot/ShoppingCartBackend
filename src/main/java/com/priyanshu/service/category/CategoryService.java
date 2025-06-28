package com.priyanshu.service.category;

import com.priyanshu.dtos.categoryDTO.CategoryRequestDTO;
import com.priyanshu.dtos.categoryDTO.CategoryResponseDTO;
import com.priyanshu.entity.Category;
import com.priyanshu.exceptions.ResourceAlreadyFoundException;
import com.priyanshu.exceptions.ResourceNotFoundException;
import com.priyanshu.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {

        // Check if the category already exists
        if (categoryRepository.findByNameIgnoreCase(requestDTO.getName()).isPresent()) {
            throw new ResourceAlreadyFoundException("Category with this name already exists");
        }

        // Convert request DTO to entity and save it
        Category category = RequestDtoToCategory(requestDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryToResponseDto(savedCategory);

    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        // Find the category by ID
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        return CategoryToResponseDto(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        // Find the existing category
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        // Check if the new name already exists
        if (categoryRepository.findByNameIgnoreCase(requestDTO.getName()).isPresent() &&
            !existingCategory.getName().equalsIgnoreCase(requestDTO.getName())) {
            throw new ResourceAlreadyFoundException("Category with this name already exists");
        }


        // Update the existing category with new values
        existingCategory.setName(requestDTO.getName());
        existingCategory.setDescription(requestDTO.getDescription());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryToResponseDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        // check the category exists or not, if not throw exception or else delete it
        categoryRepository.findById(id)
                .ifPresentOrElse(
                        categoryRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Category not found with id: " + id);
                        }
                );
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        // Fetch all categories from the repository
        List<Category> categories = categoryRepository.findAll();

        // Convert the list of Category entities to a list of CategoryResponseDTO
        return categories.stream()
                .map(this::CategoryToResponseDto)
                .toList();
    }

    // Additional methods can be implemented as needed

    Category RequestDtoToCategory(CategoryRequestDTO requestDTO) {
        // Convert CategoryRequestDTO to Category entity
        return modelMapper.map(requestDTO, Category.class);
    }


    CategoryResponseDTO CategoryToResponseDto(Category category) {
        // Convert Category entity to CategoryResponseDTO
        return modelMapper.map(category, CategoryResponseDTO.class);
    }
}
