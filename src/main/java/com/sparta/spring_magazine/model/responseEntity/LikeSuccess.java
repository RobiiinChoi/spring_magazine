package com.sparta.spring_magazine.model.responseEntity;

import com.sparta.spring_magazine.dto.request.LikeRequestDto;
import com.sparta.spring_magazine.dto.response.LikeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeSuccess {
    private String result;
    private String msg;
    private boolean likeState;
    private LikeResponseDto likeResponseDto;
}
