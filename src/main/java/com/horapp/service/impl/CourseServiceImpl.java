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
import org.webjars.NotFoundException;

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
                .map(CourseServiceImpl::getCourseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllByIdList(List<Long> idList) {
        return courseRepository.findByIdCourseInAndDeletedFalse(idList);
    }


    @Override
    public CourseResponseDTO findById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isEmpty()){
            throw new NotFoundException("Course not found with Id = " + id);
        }
        Course course = optionalCourse.get();
        return getCourseResponseDTO(course);
    }

    @Override
    public Course findEntityById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isEmpty()){
            throw new NotFoundException("Course not found with Id = " + id);
        }
        return optionalCourse.get();
    }

    @Override
    public String save(CourseRequestDTO courseRequestDTO) {
        Major major = majorService.findEntityById(courseRequestDTO.majorId());
        Course course = new Course();
        if(courseRequestDTO.userId()!= null){
            User user = userService.findEntityById(courseRequestDTO.userId());
            course.setUser(user);
        }
        if(courseRequestDTO.tableId() != null){
            TimeTable timeTable = timeTableService.findEntityById(courseRequestDTO.tableId());
            course.setTimeTable(timeTable);

        }
        course.setCourseName(courseRequestDTO.courseName());
        course.setMajor(major);
        courseRepository.save(course);
        return "Course created successfully";
    }

    @Override
    public String deleteById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isEmpty()){
            throw new NotFoundException("Course not found with Id = " + id);
        }
            Course courseToDelete = optionalCourse.get();
            courseToDelete.setDeleted(true);
            courseRepository.save(courseToDelete);
            return "The course with ID " + courseToDelete.getIdCourse()+ " was deleted successfully";
    }

    private static CourseResponseDTO getCourseResponseDTO(Course course) {
        List<String> schedules = course.getScheduleList().stream()
                .map(Schedule::getCourseGroup)
                .collect(Collectors.toList());

        return new CourseResponseDTO(
                course.getIdCourse(),
                course.getCourseName(),
                course.getMajor().getMajorName(),
                course.getUser()!= null ? course.getUser().getUsername() : null,
                course.getTimeTable() != null ? course.getTimeTable().getIdTimeTable() : null,
                schedules
        );
    }
}
