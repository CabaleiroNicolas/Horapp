package com.horapp.service;

import com.horapp.presentation.dto.request.CategoryRequestDTO;
import com.horapp.presentation.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO saveCategory(CategoryRequestDTO category);
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findById(Long id);
    String deleteById(Long id);
}
