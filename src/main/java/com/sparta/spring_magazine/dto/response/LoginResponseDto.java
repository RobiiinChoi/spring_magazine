package com.sparta.spring_magazine.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private Long userId;
    private String username;
    private String nickname;
    private String token;

    @Builder
    public LoginResponseDto(Long userId, String username, String nickname, String token) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.token = token;
    }
}
