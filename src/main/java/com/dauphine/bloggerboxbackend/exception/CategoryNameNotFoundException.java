package com.dauphine.bloggerboxbackend.exception;

public class CategoryNameNotFoundException extends RuntimeException {

    public CategoryNameNotFoundException(String name) {
        super("No category found with the name: " + name);
    }
}
