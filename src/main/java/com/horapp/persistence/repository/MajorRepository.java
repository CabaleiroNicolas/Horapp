package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {
}
