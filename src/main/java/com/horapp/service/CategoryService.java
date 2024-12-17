package com.horapp.service;

import com.horapp.persistence.entity.Category;
import com.horapp.presentation.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO category);
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    Optional<Category> updateById(Category newCategory, Long id);
    String deleteById(Long id);
}
