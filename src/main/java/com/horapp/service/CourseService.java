package com.horapp.service;

import com.horapp.persistence.entity.Course;
import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    List<CourseResponseDTO> findAll();
    List<Course> findAllOpta(List<Long> idList);
    CourseResponseDTO findById(Long id);
    Course findEntityById(Long id);
    CourseResponseDTO save(CourseRequestDTO courseRequestDTO);
    String deleteById(Long id);
}
