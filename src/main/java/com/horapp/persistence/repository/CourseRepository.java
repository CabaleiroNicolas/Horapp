package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDeletedFalse();
    List<Course> findByIdCourseInAndDeletedFalse(List<Long> ids);
}
