package com.xsis.javapos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    Optional<Variant> findByName(String name);    
}
