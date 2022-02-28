package com.sparta.spring_magazine.model.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteSuccess {
    private String result;
    private String message;
    private Long postId;
}
