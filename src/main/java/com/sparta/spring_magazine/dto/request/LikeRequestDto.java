package com.sparta.spring_magazine.dto.request;

import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class LikeRequestDto {
    private final User user;
    private final Post post;
}
