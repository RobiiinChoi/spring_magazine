package com.sparta.spring_magazine.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorException extends RuntimeException{
    private final ErrorCode errorCode;
}
