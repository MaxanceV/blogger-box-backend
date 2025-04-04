package com.dauphine.bloggerboxbackend.exception;

import java.util.UUID;

public class PostNotFoundByIdException extends RuntimeException {

    public PostNotFoundByIdException(UUID id) {
        super("Post not found with id: " + id);
    }
}
