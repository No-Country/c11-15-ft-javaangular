package com.backend.petshelter.error;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
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
    public ResponseEntity error400BadRequest(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatoserrorValidacion::new).toList(); //se atrapan los errores
            return ResponseEntity.badRequest().body(errores);
        }


        //se crea una clase inmutable para devolver solo el campo y el mensaje necesario.
        private record DatoserrorValidacion(String campo, String error){
            public DatoserrorValidacion(FieldError error){
                this(error.getField(),error.getDefaultMessage());
            }
        }

}
