package com.backend.petshelter.error;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404NotFound(){
        return  ResponseEntity.notFound().build();
    };


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400BadRequest(){
            return ResponseEntity.badRequest().build();
        }



}
