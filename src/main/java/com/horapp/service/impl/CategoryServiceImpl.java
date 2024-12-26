package com.horapp.service.impl;

import com.horapp.exception.category.CategoryCreationException;
import com.horapp.exception.category.CategoryNotFoundException;
import com.horapp.persistence.entity.Category;
import com.horapp.persistence.repository.CategoryRepository;
import com.horapp.presentation.dto.request.CategoryRequestDTO;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import com.horapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        if(categoryRequestDTO.getCategoryName().isEmpty() || categoryRequestDTO.getDescriptionName().isEmpty() ){
            throw new NullPointerException("The fields categoryName and descriptionName must no be empty");
        }
        try {
            Category category = new Category();
            ModelMapper modelMapper = new ModelMapper();
            category.setCategoryName(categoryRequestDTO.getCategoryName());
            category.setDescriptionName(categoryRequestDTO.getDescriptionName());
            categoryRepository.save(category);
            return modelMapper.map(category, CategoryResponseDTO.class);
        } catch (CategoryNotFoundException e) {
            throw new CategoryCreationException(e.getMessage(), e);
        } catch (DataIntegrityViolationException e){
            throw new CategoryCreationException("Data integrity violation while creating the category: " + e.getMessage(), e);
        } catch (Exception e){
            throw new CategoryCreationException("An unexpected error occurred while creating the category.", e);
        }
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return categoryRepository.findByDeletedFalse().stream()
                .map(category -> {
                    return getCategoryResponseDTO(category);
                })
                .collect(Collectors.toList());
    }


    @Override
    public CategoryResponseDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new CategoryNotFoundException(id);
        }
        Category categoryFind = category.get();
        return getCategoryResponseDTO(categoryFind);
    }

    @Override
    public Category findEntityById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new CategoryNotFoundException(id);
        }
            ModelMapper modelMapper = new ModelMapper();
            Category categoryFind = category.get();
            return categoryFind;
    }


    @Override
    public String deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new CategoryNotFoundException(id);
        }
            Category categoryToDelete = category.get();
            categoryToDelete.setDeleted(true);
            categoryRepository.save(categoryToDelete);
            return "The Category with ID " + categoryToDelete.getIdCategory()+ " was deleted successfully" ;
    }

    private static CategoryResponseDTO getCategoryResponseDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getCategoryName());
        categoryResponseDTO.setDescriptionName(category.getDescriptionName());
        categoryResponseDTO.setIdCategory(category.getIdCategory());
        return categoryResponseDTO;
    }
}
