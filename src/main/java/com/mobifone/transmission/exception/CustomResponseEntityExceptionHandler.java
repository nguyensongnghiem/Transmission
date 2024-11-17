package com.mobifone.transmission.exception;

import com.mobifone.transmission.model.ValidateErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        // override method of ResponseEntityExceptionHandler class
        public final ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception, WebRequest request) {
                return buildErrorResponse(
                                exception,
                                "Lỗi không xác định tại server",
                                HttpStatus.INTERNAL_SERVER_ERROR

                );
        }

        // Handle filter exception from jwt
        @ExceptionHandler(ExpiredJwtException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ResponseEntity<ErrorResponse> handleJwtExpirationException(HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Token đã hết hạn",
                                HttpStatus.UNAUTHORIZED

                );
        }

        @ExceptionHandler(SignatureException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ResponseEntity<ErrorResponse> handleSignatureException(HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Chữ ký của Token không hợp lệ",
                                HttpStatus.UNAUTHORIZED

                );
        }

        @ExceptionHandler(MalformedJwtException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleMalformedJwtException(HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Định dạng Token không hợp lệ",
                                HttpStatus.BAD_REQUEST

                );
        }
        @ExceptionHandler(UnsupportedJwtException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleUnsupportedJwtException (HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Loại Token không được hỗ trợ",
                                HttpStatus.BAD_REQUEST

                );
        }
        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException (HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Tham số không hợp lệ",
                                HttpStatus.BAD_REQUEST

                );
        }

        @ExceptionHandler(JwtException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ResponseEntity<ErrorResponse> handleJwtValidationException(HttpServletRequest request, Exception ex) {
                return buildErrorResponse(
                                ex,
                                "Token không hợp lệ",
                                HttpStatus.UNAUTHORIZED

                );
        }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(InvalidFileTypeException.class)
        public ResponseEntity<ErrorResponse> InvalidFileTypeExceptionHandler(InvalidFileTypeException ex,
                        WebRequest request) {

                return buildErrorResponse(
                                ex,
                                "Lỗi file không đúng định dạng",
                                HttpStatus.BAD_REQUEST

                );
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(SiteIdExistedException.class)
        public ResponseEntity<ErrorResponse> SiteIdExistedExceptionHandler(SiteIdExistedException ex,
                        WebRequest request) {
                return buildErrorResponse(
                                ex,
                                HttpStatus.BAD_REQUEST

                );
        }

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(SiteNotFoundException.class)
        public ResponseEntity<ErrorResponse> SiteNotFoundExceptionHandler(SiteNotFoundException ex,
                        WebRequest request) {
                return buildErrorResponse(
                                ex,
                                HttpStatus.NOT_FOUND

                );
        }

        @Override
        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                "Lỗi xác thực. Kiểm tra chi tiết tại trường 'errors'.");

                for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                        errorResponse.addValidationError(fieldError.getField(),
                                        fieldError.getDefaultMessage());
                }
                return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

        // Build ErrorResponse from exception without message
        private ResponseEntity<ErrorResponse> buildErrorResponse(
                        Exception exception,
                        HttpStatus httpStatus

        ) {
                return buildErrorResponse(
                                exception,
                                exception.getMessage(),
                                httpStatus);
        }

        // Build ErrorResponse from exception with specific message
        private ResponseEntity<ErrorResponse> buildErrorResponse(
                        Exception exception,
                        String message,
                        HttpStatus httpStatus) {
                ErrorResponse errorResponse = new ErrorResponse(
                                httpStatus.value(),
                                message);

                return ResponseEntity.status(httpStatus).body(errorResponse);
        }
}
