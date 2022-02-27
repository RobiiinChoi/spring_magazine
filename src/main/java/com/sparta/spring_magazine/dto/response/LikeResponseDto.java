package com.sparta.spring_magazine.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LikeResponseDto {
    private Long userId;
    private Long postId;

    @Builder
    public LikeResponseDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
