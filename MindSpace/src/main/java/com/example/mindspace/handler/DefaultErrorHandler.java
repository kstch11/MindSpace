package com.example.mindspace.handler;

import com.example.mindspace.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultErrorHandler {

    @ExceptionHandler({RuntimeException.class, EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleConverterException(Exception exception) {
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    record ErrorResponse(String error, String description) {}
}
