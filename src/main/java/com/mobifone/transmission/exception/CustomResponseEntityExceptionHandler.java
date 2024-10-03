package com.mobifone.transmission.exception;

import com.mobifone.transmission.model.ValidateErrorResponse;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //override method of ResponseEntityExceptionHandler class
    public final ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception, WebRequest request)
    {
        return buildErrorResponse(
                exception,
                "Xảy ra Lỗi không xác định tại server",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<ErrorResponse> InvalidFileTypeExceptionHandler(InvalidFileTypeException ex, WebRequest request) {

        return buildErrorResponse(
                ex,
                "Lỗi file không đúng định dạng",
                HttpStatus.BAD_REQUEST,
                request
        );
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SiteIdExistedException.class)
    public ResponseEntity<ErrorResponse> SiteIdExistedExceptionHandler(InvalidFileTypeException ex, WebRequest request) {
        return buildErrorResponse(
                ex,
                HttpStatus.BAD_REQUEST,
                request
        );
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<ErrorResponse> SiteNotFoundExceptionHandler(SiteNotFoundException ex, WebRequest request) {
        return buildErrorResponse(
                ex,
                HttpStatus.BAD_REQUEST,
                request
        );
    }
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Lỗi xác thực. Kiểm tra chi tiết tại trường 'errors'."
        );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }


    // Build ErrorResponse from exception without message
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        return buildErrorResponse(
                exception,
                exception.getMessage(),
                httpStatus,
                request);
    }

    // Build ErrorResponse from exception with specific message
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

