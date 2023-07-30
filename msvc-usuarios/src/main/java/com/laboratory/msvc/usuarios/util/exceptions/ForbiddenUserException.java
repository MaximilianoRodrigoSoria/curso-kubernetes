package com.laboratory.msvc.usuarios.util.exceptions;

public class ForbiddenUserException extends RuntimeException{

    public ForbiddenUserException() {
        super("This user is blocked");
    }
}
