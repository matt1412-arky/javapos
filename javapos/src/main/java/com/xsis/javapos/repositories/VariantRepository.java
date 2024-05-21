package com.xsis.javapos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Variant;
import java.util.List;


@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    Optional<Variant> findByName(String name);
    Optional<Variant> findById(long id);
    Optional<Variant> findByCategoryId(long categoryId);   
    Optional<List<Variant>> findByNameContainsIgnoreCase(String name);
    Optional<List<Variant>> findByDescriptionContainsIgnoreCase(String description);
    Optional<List<Variant>> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    Optional<List<Variant>> findByIsDeleted(boolean isDeleted);
}
