package com.stormlabs.shackcontrol.server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity onEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
