package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.javapos.models.Customer;
import com.xsis.javapos.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customer> getAll() throws Exception {
        try {
            return customerRepository.findByIsDeleted(false).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Customer Create(Customer data) throws Exception {
        Optional<Customer> customerExsist = customerRepository.findByEmail(data.getEmail());
        
        if (customerExsist.isEmpty()) {
            return customerRepository.save(data);
        } else {
            return new Customer();
        }
    }

    public Customer Update(Customer data) throws Exception {
        Optional<Customer> customerExsist = customerRepository.findById(data.getId());

        if (!customerExsist.isEmpty()) {
            // Update Field
            data.setCreateBy(customerExsist.get().getCreateBy());
            data.setCreateDate(customerExsist.get().getCreateDate());
            data.setDeleted(customerExsist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());
            
            // Update Table
            return customerRepository.save(data);
            // throw new ResponseStatusException(HttpStatus.OK, "Customer has been updated");
        } else {
            return new Customer();
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not exsist");
        }
    }

    public Customer Delete(long id, int userId) throws Exception {
        Optional<Customer> customerExsist = customerRepository.findById(id);

        if (!customerExsist.isEmpty()) {
            Customer customer = customerExsist.get();

            // Update Field
            customer.setDeleted(true);
            // custumer.setUpdateBy(userId);
            customer.setUpdateDate(LocalDateTime.now());

            // Update Table
            return customerRepository.save(customer);
        } else {
            return new Customer();
        }
    }
}
