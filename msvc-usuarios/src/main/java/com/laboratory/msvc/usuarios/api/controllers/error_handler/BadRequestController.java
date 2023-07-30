package com.laboratory.msvc.usuarios.api.controllers.error_handler;


import com.laboratory.msvc.usuarios.api.model.responses.BaseErrorResponse;
import com.laboratory.msvc.usuarios.api.model.responses.ErrorResponse;
import com.laboratory.msvc.usuarios.api.model.responses.ErrorsResponse;
import com.laboratory.msvc.usuarios.util.exceptions.IdNotFoundException;
import com.laboratory.msvc.usuarios.util.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler({IdNotFoundException.class, UsernameNotFoundException.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleIdNotFound(MethodArgumentNotValidException exception) {
        var errors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
