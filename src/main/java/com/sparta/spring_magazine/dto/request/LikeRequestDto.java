package com.sparta.spring_magazine.dto.request;

import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LikeRequestDto {
    private User user;
    private Post post;
    private boolean likeState;
    private int likeCount;
}
