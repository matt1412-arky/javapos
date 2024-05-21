package com.xsis.javapos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.Customer;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(long id);
    Optional<Customer> findByEmail(String email);
    Optional<List<Customer>> findByEmailContainsIgnoreCase(String email);
    Optional<List<Customer>> findByPhoneContainsIgnoreCase(String phone);
    Optional<List<Customer>> findByAddressContainsIgnoreCase(String address);
    // Optional<List<Customer>> findByRole_Id(int roleId);
    Optional<List<Customer>> findByIsDeleted(boolean isDeleted);    
}
