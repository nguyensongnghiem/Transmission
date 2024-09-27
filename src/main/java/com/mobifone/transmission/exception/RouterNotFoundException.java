package com.mobifone.transmission.exception;


public class RouterNotFoundException extends RuntimeException{
    public RouterNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
