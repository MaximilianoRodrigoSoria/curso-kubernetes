package com.laboratory.msvc.courses.api.controllers.error_handler;


import com.laboratory.msvc.courses.api.model.responses.BaseErrorResponse;
import com.laboratory.msvc.courses.api.model.responses.ErrorResponse;
import com.laboratory.msvc.courses.util.exceptions.ForbiddenUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenCustomerHandler {

    @ExceptionHandler(ForbiddenUserException.class)
    public BaseErrorResponse handleIdNotFound(ForbiddenUserException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }
}
