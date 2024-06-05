package com.mobifone.transmission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class SiteNotFoundException extends RuntimeException{
    public SiteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
