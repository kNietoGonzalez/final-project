package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.ApiError;
import com.example.projekt_koncowy.exceptions.BadRequestException;
import com.example.projekt_koncowy.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ApiError> handlerBadRequestException(BadRequestException exception) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(), exception.getViolations());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
