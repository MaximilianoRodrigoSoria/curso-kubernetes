package com.laboratory.msvc.courses.util.exceptions;

public class CourseExistException extends RuntimeException{

    private static final String ERROR_MESSAGE = "The ID %s no exist in %s";

    public CourseExistException(Long id, String tableName) {
        super(String.format(ERROR_MESSAGE, id, tableName));
    }

}
