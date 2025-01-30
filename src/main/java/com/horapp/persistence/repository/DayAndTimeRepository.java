package com.horapp.persistence.repository;

import com.horapp.persistence.entity.DayAndTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayAndTimeRepository extends JpaRepository<DayAndTime, Long> {
    List<DayAndTime> findAllBySchedule_IdSchedule(Long idSchedule);
}
