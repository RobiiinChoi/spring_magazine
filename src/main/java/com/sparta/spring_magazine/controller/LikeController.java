package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.response.LikeResponseDto;
import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.model.responseEntity.LikeSuccess;
import com.sparta.spring_magazine.repository.PostRepository;
import com.sparta.spring_magazine.service.LikeService;
import com.sparta.spring_magazine.service.PostService;
import com.sparta.spring_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LikeController {

    private final UserService userService;
    private final LikeService likeService;
    private final PostRepository postRepository;

    @GetMapping("/post/{postId}/like")
    public ResponseEntity<LikeSuccess> like(@PathVariable Long postId){
        if(!userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("로그인이 필요합니다");
        }
        User user = userService.getMyUserWithAuthorities().get();
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다"));
        int num = likeService.like(user, post);
        LikeResponseDto responseDto = LikeResponseDto.builder()
                .postId(post.getId())
                .userId(user.getId())
                .build();
        if (num == 1){
            return new ResponseEntity<>(new LikeSuccess("success", "좋아요 생성", true, responseDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new LikeSuccess("success", "좋아요 취소", false, responseDto), HttpStatus.OK);
        }
    }

}
