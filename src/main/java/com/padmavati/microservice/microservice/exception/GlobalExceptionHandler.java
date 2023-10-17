package com.padmavati.microservice.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> fileNotFound(FileNotFoundException fileNotFoundException){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(fileNotFoundException.getMessage());
    }
//    @ExceptionHandler(NonUniqueResultException.class)
//    public ResponseEntity<String> noUniqueResultFound(NonUniqueResultException nonUniqueResultException){
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(nonUniqueResultException.getMessage());
//    }
}
