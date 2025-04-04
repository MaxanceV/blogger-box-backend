package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.exception.CategoryAlreadyExistException;
import com.dauphine.bloggerboxbackend.exception.CategoryNameNotFoundException;
import com.dauphine.bloggerboxbackend.exception.CategoryNotFoundByIdException;
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
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        if (repository.existsByName(categoryRequest.getName())) {
            throw new CategoryAlreadyExistException(categoryRequest.getName());
        }
        Category category = new Category(categoryRequest.getName());
        return repository.save(category);
    }

    public Category update(UUID id, String name){
        Category category = getById(id);
        if (repository.existsByName(name) && !category.getName().equalsIgnoreCase(name)) {
            throw new CategoryAlreadyExistException(name);
        }

        category.setName(name);
        return repository.save(category);
    }

    public boolean deleteCategory(UUID id){
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> getAllByName(String name) {
        List<Category> results = repository.findAllByName(name);
        if (results.isEmpty()) {
            throw new CategoryNameNotFoundException(name);
        }
        return results;}
}
