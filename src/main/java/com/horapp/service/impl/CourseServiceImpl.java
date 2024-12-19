package com.horapp.service.impl;

import com.horapp.persistence.entity.*;
import com.horapp.persistence.repository.CourseRepository;
import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.MajorService;
import com.horapp.service.TimeTableService;
import com.horapp.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private TimeTableService timeTableService;

    @Override
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findByDeletedFalse().stream()
                .map(course -> {
                    return getCourseResponseDTO(course);
                })
                .collect(Collectors.toList());
    }


    @Override
    public CourseResponseDTO findById(Long id) {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if(optionalCourse.isPresent()){
                Course course = optionalCourse.get();
                return getCourseResponseDTO(course);
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
            throw new RuntimeException("Something goes wrong creating the user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        try {
            Major major = majorService.findEntityById(courseRequestDTO.getMajorId());
            Course course = new Course();
            CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
            if(courseRequestDTO.getUserId()!= null){
                User user = userService.findEntityById(courseRequestDTO.getUserId());
                course.setUser(user);
                courseResponseDTO.setUsername(user.getUsername());
            }
            if(courseRequestDTO.getTableId() != null){
                TimeTable timeTable = timeTableService.findEntityById(courseRequestDTO.getTableId());
                course.setTimeTable(timeTable);
                courseResponseDTO.setTableId(timeTable.getIdTimeTable());
            }
            course.setCourseName(courseRequestDTO.getCourseName());
            course.setMajor(major);
            courseRepository.save(course);
            courseResponseDTO.setCourseName(course.getCourseName());
            courseResponseDTO.setMajorName(major.getMajorName());
            return courseResponseDTO;
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
    private static CourseResponseDTO getCourseResponseDTO(Course course) {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        if(course.getUser()!= null){
            courseResponseDTO.setUsername(course.getUser().getUsername());
        }
        if(course.getTimeTable() != null){
            courseResponseDTO.setTableId(course.getTimeTable().getIdTimeTable());
        }
        courseResponseDTO.setCourseName(course.getCourseName());
        courseResponseDTO.setMajorName(course.getMajor().getMajorName());
        List<String> schedules = course.getScheduleList().stream()
                .map(Schedule::getCourseGroup)
                .collect(Collectors.toList());
        courseResponseDTO.setSchedules(schedules);
        return courseResponseDTO;
    }
}
