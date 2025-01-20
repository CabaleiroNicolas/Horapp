package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.presentation.dto.request.CategoryRequestDTO;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import com.horapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category(categoryRequestDTO.categoryName(), categoryRequestDTO.descriptionName());
        categoryRepository.save(category);

        return new CategoryResponseDTO(
                category.getIdCategory(),
                category.getCategoryName(),
                category.getDescriptionName()
        );
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findByDeletedFalse().stream()
                .map(CategoryServiceImpl::getCategoryResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CategoryResponseDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category not found with Id = " + id);
        }
        Category categoryFind = category.get();
        return getCategoryResponseDTO(categoryFind);
    }


    @Override
    public String deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category not found with Id = " + id);
        }
            Category categoryToDelete = category.get();
            categoryToDelete.setDeleted(true);
            categoryRepository.save(categoryToDelete);
            return "The Category with ID " + categoryToDelete.getIdCategory()+ " was deleted successfully" ;
    }

    private static CategoryResponseDTO getCategoryResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getIdCategory(),
                category.getCategoryName(),
                category.getDescriptionName()
        );
    }
}
