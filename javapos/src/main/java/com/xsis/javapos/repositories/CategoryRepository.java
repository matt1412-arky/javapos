package com.xsis.javapos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(long id);
    Optional<Category> findByName(String name);
    Optional<Category> findByNameContainsIgnoreCase(String name);
    Optional<Category> findByDescriptionContainsIgnoreCase(String description);
    Optional<Category> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
}
