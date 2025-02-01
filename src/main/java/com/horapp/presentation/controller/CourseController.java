package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;
import com.horapp.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findAll/{id}")
    public ResponseEntity<List<CourseResponseDTO>> findAll(@PathVariable Long id){
        return new ResponseEntity<>(courseService.findAllByMajor(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        return new ResponseEntity<>(courseService.save(courseRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(courseService.deleteById(id), HttpStatus.NO_CONTENT);
    }


}
