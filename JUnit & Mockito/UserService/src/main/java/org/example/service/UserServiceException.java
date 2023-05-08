package org.example.service;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String couldNotCreateUser) {
        super(couldNotCreateUser);
    }
}
