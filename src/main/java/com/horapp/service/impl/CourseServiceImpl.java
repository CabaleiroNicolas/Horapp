package com.horapp.service.impl;

import com.horapp.exception.course.CourseCreationException;
import com.horapp.exception.course.CourseNotFoundException;
import com.horapp.exception.major.MajorNotFoundException;
import com.horapp.persistence.entity.*;
import com.horapp.persistence.repository.CourseRepository;
import com.horapp.presentation.dto.request.CourseRequestDTO;
import com.horapp.presentation.dto.response.CourseResponseDTO;
import com.horapp.service.CourseService;
import com.horapp.service.MajorService;
import com.horapp.service.TimeTableService;
import com.horapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Course> findAllByIdList(List<Long> idList) {
        return courseRepository.findByIdCourseInAndDeletedFalse(idList);
    }


    @Override
    public CourseResponseDTO findById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(!optionalCourse.isPresent()){
           throw new CourseNotFoundException(id);
        }
        Course course = optionalCourse.get();
        return getCourseResponseDTO(course);
    }

    @Override
    public Course findEntityById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(!optionalCourse.isPresent()){
            throw new CourseNotFoundException(id);
        }
        return optionalCourse.get();
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
        } catch (MajorNotFoundException | CourseCreationException e) {
            throw new CourseCreationException(e.getMessage(), e);
        }catch (DataIntegrityViolationException e) {
            throw new CourseCreationException("Data integrity violation while creating the course: " + e.getMessage(), e);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public String deleteById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(!optionalCourse.isPresent()){
            throw new CourseNotFoundException(id);
        }
            Course courseToDelete = optionalCourse.get();
            courseToDelete.setDeleted(true);
            courseRepository.save(courseToDelete);
            return "The course with ID " + courseToDelete.getIdCourse()+ " was deleted successfully";
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
        courseResponseDTO.setIdCourse(course.getIdCourse());
        List<String> schedules = course.getScheduleList().stream()
                .map(Schedule::getCourseGroup)
                .collect(Collectors.toList());
        courseResponseDTO.setSchedules(schedules);
        return courseResponseDTO;
    }
}
