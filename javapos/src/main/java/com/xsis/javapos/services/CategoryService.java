package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private Optional<Category> categoryExsist;

    public List<Category> getAll() throws Exception {
        try {
            return categoryRepository.findByIsDeleted(false).get();
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
        categoryExsist = categoryRepository.findById(data.getId());

        if (!categoryExsist.isEmpty()) {
            // Update Field
            data.setCreateBy(categoryExsist.get().getCreateBy());
            data.setCreateDate(categoryExsist.get().getCreateDate());
            data.setDeleted(categoryExsist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());
            
            // Update Table
            return categoryRepository.save(data);
            // throw new ResponseStatusException(HttpStatus.OK, "Category has been updated");
        } else {
            return new Category();
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category is not exsist");
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
