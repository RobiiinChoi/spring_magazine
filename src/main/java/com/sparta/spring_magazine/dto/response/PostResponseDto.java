package com.sparta.spring_magazine.dto.response;

import com.sparta.spring_magazine.model.Layout;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
public class PostResponseDto {
    private final Long postId;
    private final String nickname;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String content;
    private final String imgUrl;
    private final int likeCount;
    private final Boolean likeState;
    private final Layout layout;
}
