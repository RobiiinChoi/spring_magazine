package com.sparta.spring_magazine.dto.response;

import com.sparta.spring_magazine.model.Layout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {

    private Long postId; // 게시물 번호
    private String nickname; // 유저 닉네임
    private String createdAt; // 작성일자
    private String modifiedAt; // 수정일자
    private String content; // 내용
    private String imgUrl; // 이미지 url
    private int likeCount; // 좋아요 수
    private boolean likeState; // 좋아요 상태
    private Layout layout; // 레이아웃

}
