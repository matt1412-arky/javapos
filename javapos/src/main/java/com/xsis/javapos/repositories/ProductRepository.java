package com.xsis.javapos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
