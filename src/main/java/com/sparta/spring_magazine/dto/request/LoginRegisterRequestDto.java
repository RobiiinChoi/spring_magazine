package com.sparta.spring_magazine.dto.request;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRegisterRequestDto {
    private String username;
    private String nickname;
    private String password;
    private String checkPw;
}
