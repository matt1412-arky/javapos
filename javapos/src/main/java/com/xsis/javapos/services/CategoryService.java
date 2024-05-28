package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private Optional<Category> categoryExsist;

    public Optional<List<Map<String, Object>>> getAll() throws Exception {
        try {
            return categoryRepository.findAllNative();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public List<Map<String, Object>> getByIdNative (long id) throws Exception {
        try {
            return categoryRepository.findByIdNative(id).get();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Optional<Category> getById(long id) throws Exception {
        try {
            return categoryRepository.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public Category Create(Category data) throws Exception {
        categoryExsist = categoryRepository.findByName(data.getName());

        if (categoryExsist.isEmpty()) {
            return categoryRepository.save(data);
        } else {
            return new Category();
        }
    }

    public Category Update(Category data) throws Exception {
        Optional<List<Map<String, Object>>> categoryExsist = categoryRepository.findByIdNative(data.getId());

        if (categoryExsist.isPresent() && !categoryExsist.get().isEmpty()) {
            Map<String, Object> categoryMap = categoryExsist.get().get(0);

            // Update Field
            data.setCreateBy((Integer) categoryMap.get("createBy"));
            // data.setCreateDate((LocalDateTime) categoryMap.get("createDate"));
            // data.setDeleted((Boolean) categoryMap.get("isDeleted"));
            data.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepository.save(data);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category does not exist");
        }
    }

    public Category Delete(long id, int userId) throws Exception {
        categoryExsist = categoryRepository.findById(id);

        if (!categoryExsist.isEmpty()) {
            Category category = categoryExsist.get();
            
            // Update Field
            category.setDeleted(true);
            category.setUpdateBy(userId);
            category.setUpdateDate(LocalDateTime.now());

            // Update Table
            return categoryRepository.save(category);
        } else {
            return new Category();
        }
    }

    public List<Category> getByName(String name) throws Exception{
        // TODO Auto-generated method stub
        return categoryRepository.findByNameContainsIgnoreCase(name)
				.orElseThrow(() -> new Exception("Category table cannot be accessed!"));
    }
}
