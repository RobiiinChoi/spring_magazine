package com.sparta.spring_magazine.dto.response;

import com.sparta.spring_magazine.model.Layout;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {

    private Long postId; // 게시물 번호
    private String nickname; // 유저 닉네임
    private LocalDateTime createdAt; // 작성일자
    private LocalDateTime modifiedAt; // 수정일자
    private String content; // 내용
    private String imgUrl; // 이미지 url
    private int likeCount; // 좋아요 수
    // private boolean likeState; // 좋아요 상태
    private Layout layout; // 레이아웃

    @Builder
    public PostResponseDto(Long postId, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt, String content, String imgUrl, int likeCount, Layout layout) {
        this.postId = postId;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.content = content;
        this.imgUrl = imgUrl;
        this.likeCount = likeCount;
        this.layout = layout;
    }
}
