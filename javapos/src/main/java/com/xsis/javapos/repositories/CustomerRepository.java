package com.xsis.javapos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
