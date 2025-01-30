package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findById(Long id);
    List<Course> findByDeletedFalse();
    List<Course> findByIdCourseInAndDeletedFalse(List<Long> ids);
    List<Course> findAllByDeletedFalseAndMajor_IdMajor(Long id);
}
