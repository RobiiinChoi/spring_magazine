package com.sparta.spring_magazine.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {
    @NotNull
    @Size(min = 3, message = "아이디는 3자 이상입니다")
    private String username;

    @NotNull
    @Size(min = 4, message = "비밀번호는 4자 이상입니다")
    private String password;

}