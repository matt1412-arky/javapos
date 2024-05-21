package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    private Optional<Product> productExsist;
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

    public Product Create(Product data) throws Exception {
        productExsist = productRepository.findByName(data.getName());

        if (productExsist.isEmpty()) {
            return productRepository.save(data);
        } else {
            return new Product();
        }
    }
    
    public Product Update(Product data) throws Exception {
        productExsist = productRepository.findById(data.getId());

        if (!productExsist.isEmpty()) {
            // Update Field
            data.setVariantId(productExsist.get().getVariantId());
            data.setCreateBy(productExsist.get().getCreateBy());
            data.setCreateDate(productExsist.get().getCreateDate());
            data.setDeleted(productExsist.get().isDeleted());
            data.setUpdateDate(productExsist.get().getUpdateDate());

            // Update Table
            return productRepository.save(data);
        } else {
            return new Product();
        }
    }

    public Product Delete(long id, long variantId) throws Exception {
        productExsist = productRepository.findById(id);

        if (!productExsist.isEmpty()) {
            Product product = productExsist.get();

            // Update Field
            product.setDeleted(true);
            product.setUpdateDate(LocalDateTime.now());

            // Update Table
            return productRepository.save(product);
        } else {
            return new Product();
        }
    }
}
