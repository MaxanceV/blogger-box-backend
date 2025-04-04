package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    public List<Category> getAll(){
        return repository.findAll();
    }

    public Category getById(UUID id){
        return repository.findById(id)
                .orElse(null);
    }

    public Category create(String name){
        Category category = new Category(name);
        return repository.save(category);
    }

    public Category update(UUID id, String name){
        Category category = getById(id);
        if(category == null){
            return null;
        }
        category.setName(name);
        return repository.save(category);
    }

    public boolean deleteCategory(UUID id){
        repository.deleteById(id);
        return true;
    }
}
