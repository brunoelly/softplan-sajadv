package com.softplan.sajadv.user.exception;

public class UserNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public UserNotCreatedException(Long id) {
        super(String.valueOf(id));
    }
}
