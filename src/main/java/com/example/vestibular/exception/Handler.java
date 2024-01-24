package com.example.vestibular.exception;

import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Handler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(NoResultException.class)
        public ResponseEntity<?> NotFoundException(NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    e.getMessage()
            );
        }
}
