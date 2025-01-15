package com.mobifone.transmission.exception;


public class FoNotFoundException extends RuntimeException{
    public FoNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
