package com.dauphine.bloggerboxbackend.controllers;

import com.dauphine.bloggerboxbackend.dto.CategoryRequest;
import com.dauphine.bloggerboxbackend.exception.CategoryNotFoundByIdException;
import com.dauphine.bloggerboxbackend.models.Category;
import com.dauphine.bloggerboxbackend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name="Category API",
        description = "Category endpoints"
)
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter like name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name){
        List<Category> categories = null;
        if(name == null){
            categories = categoryService.getAll();
        } else {
            categories = categoryService.getAllByName(name);
        }
        return ResponseEntity.ok(categories);

    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get category by id",
            description = "Retrieve a category by id"
    )
    public ResponseEntity<Category> getById(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Create new category, only required field is the name of the category to create"
    )
    public ResponseEntity<Category> create(CategoryRequest categoryRequest){
        Category category = categoryService.create(categoryRequest);
        return ResponseEntity.created(URI.create("v1/categories/"+ category.getUuid()))
                .body(category);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a category by ID",
            description = "Update the name of a category by its ID"
    )
    public ResponseEntity<Category> update(@PathVariable UUID id, @RequestBody String name){
        Category updated = categoryService.update(id, name);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category by ID",
            description = "Delete a category and return 204 if successful"
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
