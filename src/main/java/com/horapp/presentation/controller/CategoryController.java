package com.horapp.presentation.controller;


import com.horapp.presentation.dto.request.CategoryRequestDTO;
import com.horapp.presentation.dto.request.TimeTableOptaRequestDTO;
import com.horapp.presentation.dto.response.CategoryResponseDTO;
import com.horapp.presentation.dto.response.ExceptionResponse;
import com.horapp.presentation.dto.response.TimeTableOptaResponseDTO;
import com.horapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    public ResponseEntity<CategoryResponseDTO> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Deshabilitar una Category",
            description = "Deshabilita una categoria",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Path variable con el id de la categoria que se desea deshabilitar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TimeTableOptaRequestDTO.class)
                    )
            ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Category Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
