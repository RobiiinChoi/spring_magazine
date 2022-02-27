package com.sparta.spring_magazine.model.responseEntity;

import com.sparta.spring_magazine.dto.response.LikeResponseDto;
import com.sparta.spring_magazine.exception.RestApiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fail {
    private String fail;
    private RestApiException restApiException;
}
