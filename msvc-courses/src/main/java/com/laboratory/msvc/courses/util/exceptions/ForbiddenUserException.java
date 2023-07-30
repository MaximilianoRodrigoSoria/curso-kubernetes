package com.laboratory.msvc.courses.util.exceptions;

public class ForbiddenUserException extends RuntimeException{

    public ForbiddenUserException() {
        super("This user is blocked");
    }
}
