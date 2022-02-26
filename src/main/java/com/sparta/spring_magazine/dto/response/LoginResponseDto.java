package com.sparta.spring_magazine.dto.response;

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
}
