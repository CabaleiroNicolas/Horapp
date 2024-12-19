package com.horapp.persistence.repository;

import com.horapp.persistence.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable ,Long> {
    List<TimeTable> findByDeletedFalse();
}
