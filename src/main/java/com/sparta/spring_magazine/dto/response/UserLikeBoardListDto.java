package com.sparta.spring_magazine.dto.response;

import com.sparta.spring_magazine.model.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserLikeBoardListDto {
    private Long likesId;

    public UserLikeBoardListDto(Like likesId) {
        this.likesId = likesId.getId();
    }
}
