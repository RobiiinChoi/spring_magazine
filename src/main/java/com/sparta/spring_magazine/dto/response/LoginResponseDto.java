package com.sparta.spring_magazine.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class LoginResponseDto {

    private Long userId;

    private String username;

    private String nickname;

    private List<UserLikeBoardListDto> likes_id;

    private String token;

    @Builder
    public LoginResponseDto(Long userId, String username, String nickname, List<UserLikeBoardListDto> likes_id, String token) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.likes_id = likes_id;
        this.token = token;
    }
}
