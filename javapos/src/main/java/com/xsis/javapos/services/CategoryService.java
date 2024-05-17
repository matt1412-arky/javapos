package com.xsis.javapos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll() throws Exception {
        try {
            List<Category> data = categoryRepository.findAll();
            if(data.size() > 0) {
                return data;
            } else {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Category table has no data");
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public void Create(Category data) {
        Optional<Category> categoryExsist = categoryRepository.findByName(data.getName());

        if (categoryExsist.isEmpty()) {
            categoryRepository.save(data);
            throw new ResponseStatusException(HttpStatus.CREATED, "New Category saved!");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exist");
        }
    }
}
