package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Product;
import com.xsis.javapos.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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
    public ResponseEntity<?> Create(@RequestBody final Product data) {
        //TODO: process POST request
        try {
            Product newProduct = productSvc.Create(data);
            return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody final Product data) {
        //TODO: process PUT request
        try {
            Product updatedProduct = productSvc.Update(data);

            if (updatedProduct.getId() > 0) {
                return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Product does not exsist!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{varianId}")
    public ResponseEntity<?> Delete(@PathVariable final long id, @PathVariable final long varianId) {
        //TODO: process DELETE request
        try {
            Product deletedProduct = productSvc.Delete(id, varianId);

            if (deletedProduct.getId() > 0) {
                return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
            } else{
                return new ResponseEntity<String>("Product does not exsist!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
