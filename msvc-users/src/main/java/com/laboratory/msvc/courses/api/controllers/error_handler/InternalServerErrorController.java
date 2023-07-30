package com.laboratory.msvc.courses.api.controllers.error_handler;


import com.laboratory.msvc.courses.api.model.responses.BaseErrorResponse;
import com.laboratory.msvc.courses.api.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
