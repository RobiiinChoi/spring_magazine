package com.sparta.spring_magazine.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    private String successOrFail;
    private String errorMessage;
    private HttpStatus httpStatus;
}
