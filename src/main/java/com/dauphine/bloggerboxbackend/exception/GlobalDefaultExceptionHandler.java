package com.dauphine.bloggerboxbackend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({
            CategoryNotFoundByIdException.class,
            CategoryNameNotFoundException.class,
            PostNotFoundByIdException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler({
            CategoryAlreadyExistException.class,
            PostAlreadyExistException.class
    })
    public ResponseEntity<String> handleAlreadyExistException(Exception ex) {
        logger.warn("[CONFLICT] {}", ex.getMessage());
        return ResponseEntity.status(409).body(ex.getMessage());
    }
}
