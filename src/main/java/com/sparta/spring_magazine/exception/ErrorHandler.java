package com.sparta.spring_magazine.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ErrorException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(ErrorException e) {
        log.error("Error - ErrorException : " + e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

}
