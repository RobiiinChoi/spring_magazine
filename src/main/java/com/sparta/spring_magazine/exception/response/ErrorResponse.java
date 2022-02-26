package com.sparta.spring_magazine.exception.response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    // 에러는 데이터를 주지 않는다.
    private String ok = "fail"; // success , fail 2가지 중에 하나만 나온다.
    private String message;
    private String exceptionType; // 예외 타입

    @Builder
    public ErrorResponse(String message) {
        this.message = message;
    }

    @Builder
    public ErrorResponse(String message, String exceptionType) {
        this.message = message;
        this.exceptionType = exceptionType;
    }
}
