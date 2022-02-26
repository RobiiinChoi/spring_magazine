package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.request.PostRequestDto;
import com.sparta.spring_magazine.dto.response.PostResponseDto;
import com.sparta.spring_magazine.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Service
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostRequestDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto){
        if (!userService.get)
    }

}
