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
    private String username;
    private String password;

}