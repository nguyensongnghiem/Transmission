package com.mobifone.transmission.utils.hash;

public interface hashing {
    public String hashPassword(String password);
    public boolean validatePassword(String originalPassword, String storedPassword);
}
