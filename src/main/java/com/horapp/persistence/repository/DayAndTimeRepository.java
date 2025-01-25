package com.horapp.persistence.repository;

import com.horapp.persistence.entity.DayAndTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayAndTimeRepository extends JpaRepository<DayAndTime, Long> {
}
