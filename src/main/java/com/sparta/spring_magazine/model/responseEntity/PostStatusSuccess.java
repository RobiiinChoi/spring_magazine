package com.sparta.spring_magazine.model.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostStatusSuccess {
    private String result;
    private String msg;
    private Long postId;
}
