package com.sparta.spring_magazine.dto.request;

import com.sparta.spring_magazine.model.Layout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class PostRequestDto {
    @NotNull
    private String imgUrl;

    @NotNull
    private String content;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Layout layout;
}
