package com.dauphine.bloggerboxbackend.exception;

import java.util.UUID;

public class CategoryNotFoundByIdException extends RuntimeException {

    public CategoryNotFoundByIdException(UUID id) {
        super("Category not found with id: " + id);
    }
}
