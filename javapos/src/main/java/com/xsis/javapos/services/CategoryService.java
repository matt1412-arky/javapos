package com.xsis.javapos.services;

import java.time.LocalDateTime;
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

    public void Update(Category data) {
        Optional<Category> categoryExsist = categoryRepository.findById(data.getId());

        if (!categoryExsist.isEmpty()) {
            // Update Field
            data.setCreateBy(categoryExsist.get().getCreateBy());
            data.setCreateDate(categoryExsist.get().getCreateDate());
            data.setDeleted(categoryExsist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());
            
            // Update Table
            categoryRepository.save(data);
            throw new ResponseStatusException(HttpStatus.OK, "Category has been updated");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not exsist");
        }
    }

    public void Delete(long id, int userId) {
        Optional<Category> categoryExsist = categoryRepository.findById(id);

        if (!categoryExsist.isEmpty()) {
            Category category = categoryExsist.get();
            
            // Update Field
            category.setDeleted(true);
            category.setUpdateBy(userId);
            category.setUpdateDate(LocalDateTime.now());

            // Update Table
            categoryRepository.save(category);
            throw new ResponseStatusException(HttpStatus.OK, "Category has been deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not exsist");
        }
    }
}
