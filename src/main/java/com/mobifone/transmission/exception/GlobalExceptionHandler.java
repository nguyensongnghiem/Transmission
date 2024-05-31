package com.mobifone.transmission.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SiteNotFoundException.class)
    public String SiteNotFoundExceptionHandler() {
        return "site-not-found";
    }
}
