package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Product;
import com.xsis.javapos.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
public class ProductApiController {
    @Autowired
    private ProductService productSvc;
    
    @GetMapping("")
    public ResponseEntity<List<Product>> get() {
        try {
            List<Product> data = productSvc.getAll();
            return new ResponseEntity<List<Product>>(data, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public void Create(@RequestBody final Product data) {
        //TODO: process POST request
        productSvc.Create(data);
    }
    
}
