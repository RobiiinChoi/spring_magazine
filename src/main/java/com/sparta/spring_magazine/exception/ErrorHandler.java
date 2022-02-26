package com.sparta.spring_magazine.exception;

import com.sparta.spring_magazine.exception.response.NotValidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    //  Bean Validation에서 터지는 에러
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NotValidResponse notValid(MethodArgumentNotValidException e){
        // 에러가 난 입력값이 여러개여도 하나씩만 보여준다.
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        return NotValidResponse.builder()
                .field(fieldError.getField())               // 오류난 필드명
                .value(fieldError.getRejectedValue())       // 거절된 입력 값
                .message(fieldError.getDefaultMessage())    // 설정한 메세지
                .build();
    }

}
