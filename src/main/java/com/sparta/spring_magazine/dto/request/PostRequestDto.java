package com.sparta.spring_magazine.dto.request;

import com.sparta.spring_magazine.model.Layout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class PostRequestDto {
    private final String imgUrl;
    private final String content;
    @Enumerated(EnumType.STRING)
    private final Layout layout;
}
