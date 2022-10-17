package com.rest.api.dorado.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("Couldn't found user with id " + id);
    }
}
