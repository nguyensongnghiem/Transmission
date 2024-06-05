package com.mobifone.transmission.exception;

public class SiteIdExistedException extends RuntimeException{
    public SiteIdExistedException(String errorMessage) {
        super(errorMessage);
    }
}
