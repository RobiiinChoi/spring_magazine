package com.sparta.spring_magazine.dto.request;

import com.sparta.spring_magazine.model.Layout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@RequiredArgsConstructor
@Getter
@Setter
public class PostUpdateDto {
    private final String imgUrl;
    private final String content;
}
