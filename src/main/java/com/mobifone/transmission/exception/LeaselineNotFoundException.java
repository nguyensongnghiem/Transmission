package com.mobifone.transmission.exception;


public class LeaselineNotFoundException extends RuntimeException{
    public LeaselineNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
