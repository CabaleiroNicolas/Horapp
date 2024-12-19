package com.horapp.presentation.controller;


import com.horapp.presentation.dto.request.CategoryRequestDTO;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import com.horapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
