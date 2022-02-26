package com.sparta.spring_magazine.model.responseEntity;

import com.sparta.spring_magazine.dto.response.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPostSuccess {
    private String result;
    private String msg;
    private List<PostResponseDto> postResponseDto;
}
