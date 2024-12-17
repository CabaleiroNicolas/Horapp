package com.horapp.service.impl;

import com.horapp.persistence.entity.Category;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.presentation.dto.CategoryDTO;
import com.horapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Category category = modelMapper.map(categoryDTO, Category.class);
            categoryRepository.save(category);
            return categoryDTO;
        } catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the category: " + e.getMessage());
        }
    }

    @Override
    public List<CategoryDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return categoryRepository.findByDeletedFalse().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public CategoryDTO findById(Long id) {
        try {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            Category categoryFind = category.get();
            return modelMapper.map(categoryFind, CategoryDTO.class);
        }
        } catch (Exception e) {
            System.err.println("Category with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Optional<Category> updateById(Category newCategory, Long id) {
        return Optional.empty();
    }

    @Override
    public String deleteById(Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()){
                Category categoryToDelete = category.get();
                categoryToDelete.setDeleted(true);
                categoryRepository.save(categoryToDelete);
                return "The Category with ID " + categoryToDelete.getIdCategory()+ " was deleted successfully" ;
            }
        } catch (Exception e) {
            System.err.println("Category with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return "";
    }
}
