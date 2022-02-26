package com.sparta.spring_magazine.model.responseEntity;

import com.sparta.spring_magazine.dto.response.LoginResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccess {
    private String result;
    private String message;
    private LoginResponseDto responseDto;
}
