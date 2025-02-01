package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Major;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByDeletedFalseAndUserIsNull();
    List<Major> findByDeletedFalseAndUser_IdUser(Long id);
}
