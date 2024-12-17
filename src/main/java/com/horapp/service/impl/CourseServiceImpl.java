package com.horapp.service.impl;

import com.horapp.persistence.entity.Course;
import com.horapp.persistence.entity.Major;
import com.horapp.persistence.repository.CourseRepository;
import com.horapp.presentation.dto.CourseRequestDTO;
import com.horapp.presentation.dto.CourseResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.MajorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MajorService majorService;

    @Override
    public List<CourseResponseDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return courseRepository.findByDeletedFalse().stream()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO findById(Long id) {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if(optionalCourse.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                Course course = optionalCourse.get();
                return modelMapper.map(course, CourseResponseDTO.class);
            }
        } catch (Exception e) {
            System.err.println("Course with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Course findEntityById(Long id) {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if(optionalCourse.isPresent()){
                Course course = optionalCourse.get();
                return course;
            }
        } catch (Exception e) {
            System.err.println("Course with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public CourseRequestDTO save(CourseRequestDTO courseRequestDTO) {
        try {
            Major major = majorService.findEntityById(courseRequestDTO.getMajorId());
            Course course = new Course();
            course.setCourseName(courseRequestDTO.getCourseName());
            course.setMajor(major);
            courseRepository.save(course);
            return courseRequestDTO;
        } catch (Exception e) {
            throw new RuntimeException("Something goes wrong creating the course: " + e.getMessage());
        }
    }

    @Override
    public String deleteById(Long id) {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if(optionalCourse.isPresent()){
                Course courseToDelete = optionalCourse.get();
                courseToDelete.setDeleted(true);
                courseRepository.save(courseToDelete);
                return "The Category with ID " + courseToDelete.getIdCourse()+ " was deleted successfully";
            }
        } catch (Exception e) {
            System.err.println("Course with ID: " + e.getMessage() + "don´t exists in database");
            throw new RuntimeException(e);
        }
        return "";
    }
}
