package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import com.horapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findByDeletedFalse().stream()
                .map(CategoryServiceImpl::getCategoryResponseDTO)
                .collect(Collectors.toList());
    }


    private static CategoryResponseDTO getCategoryResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getIdCategory(),
                category.getCategoryName(),
                category.getDescriptionName()
        );
    }
}
