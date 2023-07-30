package com.laboratory.msvc.usuarios.util.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "The ID %s no exist in %s";

    public UsernameNotFoundException(Long id, String tableName) {
        super(String.format(ERROR_MESSAGE, id, tableName));
    }

}
