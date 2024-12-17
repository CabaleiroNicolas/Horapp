package com.horapp.service;

import com.horapp.persistence.entity.Course;
import com.horapp.presentation.dto.CourseRequestDTO;
import com.horapp.presentation.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    List<CourseResponseDTO> findAll();
    CourseResponseDTO findById(Long id);
    Course findEntityById(Long id);
    CourseRequestDTO save(CourseRequestDTO courseRequestDTO);
    String deleteById(Long id);
}
