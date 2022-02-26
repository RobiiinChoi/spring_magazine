package com.sparta.spring_magazine.exception;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String error;
    private String errorCode;
    private String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .errorCode(errorCode.getErrorCode())
                        .message(errorCode.getErrorMessage())
                        .build()
                );
    }
}
