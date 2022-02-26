package com.sparta.spring_magazine.exception.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class NotValidResponse {

    private String field; // 에러가 난 필드
    private Object value; // 입력으로 들어온 값
    private String message; // 보낼 메세지

}
