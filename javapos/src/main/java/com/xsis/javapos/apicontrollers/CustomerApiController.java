package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Customer;
import com.xsis.javapos.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
    @Autowired
    private CustomerService customerSvc;
    
    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Customer> data = customerSvc.getAll();
            
            if (data.size() > 0) {
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody final Customer data) {
        //TODO: process POST request
        try {
            Customer newCustomer = customerSvc.Create(data);
            return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update (@RequestBody final Customer data) {
        //TODO: process PUT request
        try {
            Customer updateCustomer = customerSvc.Update(data);
            return new ResponseEntity<Customer>(updateCustomer, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    @DeleteMapping("/{id}/{roleId}")
    public ResponseEntity<?> Update (@PathVariable final long id, @PathVariable int roleId) {
        //TODO: process DELETE request
        try {
            Customer deletedCustomer = customerSvc.Delete(id, roleId);

            if (deletedCustomer.getId() > 0) {
                return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
