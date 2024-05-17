package com.xsis.javapos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xsis.javapos.models.Product;
import com.xsis.javapos.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAll() throws Exception {
        try {
            List<Product> data = productRepository.findAll();
            if (data.size() > 0) {
                return data;
            } else {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product table has no data");
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
}
