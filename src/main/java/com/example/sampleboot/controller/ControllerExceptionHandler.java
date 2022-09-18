package com.example.sampleboot.controller;

import com.example.sampleboot.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {PersonNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> personNotFoundException(PersonNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }
}
