package com.horapp.persistence.repository;

import com.horapp.persistence.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable ,Long> {
}
