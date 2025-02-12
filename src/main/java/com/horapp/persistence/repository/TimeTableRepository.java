package com.horapp.persistence.repository;

import com.horapp.persistence.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable ,Long> {
}
