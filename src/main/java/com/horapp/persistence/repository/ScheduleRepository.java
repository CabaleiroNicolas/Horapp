package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.idSchedule = :scheduleId")
    Optional<Schedule> findById(Long scheduleId);

    List<Schedule> findAllByCourse_IdCourse(Long id);
}
