package com.dauphine.bloggerboxbackend.exception;

public class PostAlreadyExistException extends RuntimeException {
    public PostAlreadyExistException(String title) {
        super("A post with the title '" + title + "' already exists.");
    }
}
