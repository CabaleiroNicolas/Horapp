package com.horapp.presentation.controller;

import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;
import com.horapp.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{majorId}")
    public ResponseEntity<List<CourseResponseDTO>> findAll(@PathVariable Long majorId){
        return new ResponseEntity<>(courseService.findAllByMajor(majorId), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.findById(courseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        return new ResponseEntity<>(courseService.save(courseRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteById(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.deleteById(courseId), HttpStatus.NO_CONTENT);
    }


}
