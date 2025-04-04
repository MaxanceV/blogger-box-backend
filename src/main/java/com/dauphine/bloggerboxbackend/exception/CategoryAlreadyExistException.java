package com.dauphine.bloggerboxbackend.exception;

public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException(String name) {
        super("A category with the name '" + name + "' already exists.");
    }
}
