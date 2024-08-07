package com.mobifone.transmission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    //override method of ResponseEntityExceptionHandler class
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
    //creating exception response structure
        ErrorResponse exceptionResponse= new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    //returning exception structure and specific status
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<ErrorResponse> InvalidFileTypeExceptionHandler(InvalidFileTypeException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SiteIdExistedException.class)
    public ResponseEntity<ErrorResponse> SiteIdExistedExceptionHandler(InvalidFileTypeException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<ErrorResponse> SiteNotFoundExceptionHandler(SiteNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}

