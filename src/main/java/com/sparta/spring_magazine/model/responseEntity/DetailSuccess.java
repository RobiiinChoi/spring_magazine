package com.sparta.spring_magazine.model.responseEntity;

import com.sparta.spring_magazine.dto.response.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailSuccess {
    private String result;
    private String msg;
    private PostResponseDto postResponseDto;
}
