package com.horapp.persistence.repository;

import com.horapp.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByDeletedFalse();
    Optional<Category> findByCategoryName(String name);
}
