package com.dauphine.bloggerboxbackend.services;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final List<Category> categories;

    public CategoryService() {
        this.categories = new ArrayList<Category>();
    }

    public List<Category> getAll(){
        return categories;
    }

    public Category getById(UUID id){
        Category ret = null;
        for(Category category : categories){
            if(category.getUuid().equals(id)){
                ret = category;
            }
        }
        return ret;
    }

    public Category create(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setUuid(UUID.randomUUID());
        categories.add(category);
        return category;
    }

    public Category update(UUID id, String name){
        Category ret = getById(id);
        ret.setName(name);
        return ret;
    }

    public UUID deleteCategory(UUID id){
        if(!categories.contains(getById(id))){
            return null;
        }
        categories.remove(getById(id));
        return id;
    }

}
