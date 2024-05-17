package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Customer;
import com.xsis.javapos.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
    @Autowired
    private CustomerService customerSvc;
    @GetMapping("")
    public ResponseEntity<List<Customer>> get() {
        try {
            List<Customer> data = customerSvc.getAll();
            return new ResponseEntity<List<Customer>>(data, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
    }
}
