package com.softplan.sajadv.user.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.valueOf(id));
    }
}