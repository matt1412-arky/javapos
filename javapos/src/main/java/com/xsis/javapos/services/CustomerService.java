package com.xsis.javapos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xsis.javapos.models.Customer;
import com.xsis.javapos.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customer> getAll() throws Exception {
        try {
            List<Customer> data = customerRepository.findAll();
            if (data.size() > 0) {
                return data;
            } else {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Customer table has no data");
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}
