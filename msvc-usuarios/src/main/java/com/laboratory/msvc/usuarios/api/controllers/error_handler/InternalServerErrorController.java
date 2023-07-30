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
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorController {

    @ExceptionHandler({Exception.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }



}
