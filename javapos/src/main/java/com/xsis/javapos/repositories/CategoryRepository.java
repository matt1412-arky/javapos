package com.xsis.javapos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(long id);
    Optional<Category> findByName(String name);
    Optional<List<Category>> findByNameContainsIgnoreCase(String name);
    Optional<List<Category>> findByDescriptionContainsIgnoreCase(String description);
    Optional<List<Category>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    Optional<List<Category>> findByIsDeleted(boolean isDeleted);
}
