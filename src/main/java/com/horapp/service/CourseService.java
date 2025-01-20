package com.horapp.service;

import com.horapp.persistence.entity.Course;
import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    List<CourseResponseDTO> findAll();
    List<Course> findAllByIdList(List<Long> idList);
    CourseResponseDTO findById(Long id);
    String save(CourseRequestDTO courseRequestDTO);
    String deleteById(Long id);
}
