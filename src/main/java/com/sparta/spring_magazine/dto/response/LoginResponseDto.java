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

    @Builder
    public LoginResponseDto(Long userId, String username, String nickname) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
    }
}
