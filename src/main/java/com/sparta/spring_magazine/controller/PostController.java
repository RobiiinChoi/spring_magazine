package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.request.PostRequestDto;
import com.sparta.spring_magazine.dto.request.PostUpdateDto;
import com.sparta.spring_magazine.dto.response.PostResponseDto;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.model.responseEntity.*;
import com.sparta.spring_magazine.service.PostService;
import com.sparta.spring_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    // 게시판 글 생성
    @PostMapping("/post")
    public ResponseEntity<PostStatusSuccess> createPost(@Valid @RequestBody PostRequestDto postRequestDto){
            if(!userService.getMyUserWithAuthorities().isPresent()){
                throw new IllegalArgumentException("게시물을 작성하려면 로그인 하십시오");
            }

            User user = userService.getMyUserWithAuthorities().get();
            Long postId = postService.createPost(user, postRequestDto);
            return new ResponseEntity<>(new PostStatusSuccess("success", "게시물 등록", postId), HttpStatus.OK);
    }

    // 게시판 글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<DeleteSuccess> deletePost(@PathVariable Long postId){
        if(!userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("게시물을 삭제하려면 로그인 하십시오");
        }
        User user = userService.getMyUserWithAuthorities().get();
        postService.deletePost(postId, user);
        return new ResponseEntity<>(new DeleteSuccess("success", "게시물 삭제", postId), HttpStatus.OK);
    }

    // 게시판 글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<DeleteSuccess> updatePost(@PathVariable Long postId, @RequestBody PostUpdateDto updateDto){
        if(!userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("게시물을 삭제하려면 로그인 하십시오");
        }
        User user = userService.getMyUserWithAuthorities().get();
        postService.updatePost(postId, updateDto, user);
        return new ResponseEntity<>(new DeleteSuccess("success", "게시물 업데이트", postId), HttpStatus.OK);
    }

    // 상세 게시물 보기
    @GetMapping("/post/{postId}")
    public ResponseEntity<DetailSuccess> readDetail(@PathVariable Long postId) {
        if(!userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("상세 게시물을 확인하려면 로그인 하십시오");
        }
        User user = userService.getMyUserWithAuthorities().get();
        PostResponseDto responseDto = postService.readDetail(postId, user.getId());
        return new ResponseEntity<>(new DetailSuccess("success", "상세 게시물 보기", responseDto), HttpStatus.OK);
    }

    // 전체 게시물 보기
    @GetMapping("/post")
    public ResponseEntity<AllPostSuccess> readAll(@PageableDefault(size = 5) Pageable pageable){
        Long userId = Long.valueOf(-1); // 원래 guest라는 값을 넣을려고 했는데 type이 Long인걸 까먹음.. 0도 가능한지 확인?
            if (userService.getMyUserWithAuthorities().isPresent()){
                userId = userService.getMyUserWithAuthorities().get().getId();
            }
        List<PostResponseDto> responseDtoList = postService.readAll(pageable, userId);
        return new ResponseEntity<>(new AllPostSuccess("success", "전체 게시물 보기", responseDtoList), HttpStatus.OK);
    }

}
