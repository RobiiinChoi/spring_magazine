package com.sparta.spring_magazine.exception;

import com.sparta.spring_magazine.model.responseEntity.Fail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler {

    // Valid Check
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Fail> illegal(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(new Fail("fail", restApiException), HttpStatus.OK);
    }

    // 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Fail> accessDenied(AccessDeniedException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.UNAUTHORIZED);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(new Fail("fail", restApiException), HttpStatus.OK);
    }

    // 403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {HttpClientErrorException.Forbidden.class})
    public ResponseEntity<Fail> forbidden(HttpClientErrorException.Forbidden ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.FORBIDDEN);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(new Fail("fail", restApiException), HttpStatus.OK);
    }

    // 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {HttpClientErrorException.NotFound.class})
    public ResponseEntity<Fail> handleApiRequestException(HttpClientErrorException.Forbidden ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.FORBIDDEN);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(new Fail("fail", restApiException), HttpStatus.OK);
    }

    // 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<Fail> accessDenied(HttpServerErrorException.InternalServerError ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(new Fail("fail", restApiException), HttpStatus.OK);
    }

}