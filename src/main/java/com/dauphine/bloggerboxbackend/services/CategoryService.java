package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface CategoryService {


    public List<Category> getAll();

    public Category getById(UUID id);

    public Category create(CategoryRequest categoryRequest);

    public Category update(UUID id, String name);

    public boolean deleteCategory(UUID id);

    public List<Category> getAllByName(String name);
}
