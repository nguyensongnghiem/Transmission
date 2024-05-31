package com.mobifone.transmission.exception;

public class SiteNotFoundException extends RuntimeException{
    public SiteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
