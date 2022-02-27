package com.sparta.spring_magazine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler {

    // Valid Check
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> illegal(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setSuccessOrFail("fail");
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                restApiException,
                HttpStatus.OK
        );
    }

    // 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> accessDenied(AccessDeniedException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setSuccessOrFail("fail");
        restApiException.setHttpStatus(HttpStatus.UNAUTHORIZED);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                restApiException,
                HttpStatus.OK
        );
    }

    // 403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {HttpClientErrorException.Forbidden.class})
    public ResponseEntity<Object> forbidden(HttpClientErrorException.Forbidden ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setSuccessOrFail("fail");
        restApiException.setHttpStatus(HttpStatus.FORBIDDEN);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                restApiException,
                HttpStatus.OK
        );
    }

    // 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {HttpClientErrorException.NotFound.class})
    public ResponseEntity<Object> handleApiRequestException(HttpClientErrorException.Forbidden ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setSuccessOrFail("fail");
        restApiException.setHttpStatus(HttpStatus.FORBIDDEN);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                restApiException,
                HttpStatus.OK
        );
    }

}