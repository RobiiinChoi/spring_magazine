package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.PostRequestDto;
import com.sparta.spring_magazine.dto.request.PostUpdateDto;
import com.sparta.spring_magazine.dto.response.PostResponseDto;
import com.sparta.spring_magazine.model.Like;
import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 생성
    public Long createPost(User user, PostRequestDto requestDto) {
        if (requestDto.getContent().length() == 0 || requestDto.getImgUrl() == null){
            throw new IllegalArgumentException("글, 사진을 작성해주세요");
        }
        if (requestDto.getLayout() == null ){
            throw new IllegalArgumentException("레이아웃을 선택해 주세요");
        }
        Post post = new Post(user, requestDto);
        Long postId = postRepository.save(post).getId();
        return postId;
    }

    // 글 삭제
    public Long deletePost(Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        User writer = post.getUser();
        if (!Objects.equals(user.getId(), writer.getId())){
            throw new IllegalArgumentException("글 작성자만 삭제 가능합니다.");
        }
        Long deletePostId = post.getId();
        postRepository.deleteById(postId);
        return deletePostId;
    }

    // 글 수정
    public Long updatePost(Long postId, PostUpdateDto updateDto, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        User writer = post.getUser();
        if (!Objects.equals(user.getId(), writer.getId())){
            throw new IllegalArgumentException("글 작성자만 수정 가능합니다.");
        }
        if (updateDto.getContent().length() == 0 || updateDto.getImgUrl() == null){
            throw new IllegalArgumentException("글, 사진을 수정해주세요");
        }
        post.update(user, updateDto);
        return postId;
    }

    // 전체 게시판 게시물
    @Cacheable("boardRead")
    public List<PostResponseDto> readAll(Pageable pageable, Long userId){

        List<Post> posts = postRepository.findAllFetched(pageable);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts){
            PostResponseDto postResponseDto = putAllInfo(post, userId);
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
    }

    // 상세 게시판 게시물
    public PostResponseDto readDetail(Long postId, Long userId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        PostResponseDto responseDto = putAllInfo(post, userId);
        return responseDto;
    }

    // post & 좋아요 여부 집어넣을 함수
    public PostResponseDto putAllInfo(Post post, Long userId){
        List<Like> likes = post.getLikes();
        int likeCount = (int)post.getLikes().size();
        Boolean check = false;
        for (Like like : likes){
            if (like.getUser().getId() == userId) {
                check = true;
                break;
            }
        }
        PostResponseDto showInfo = PostResponseDto.builder()
                .userId(post.getUser().getId())
                .postId(post.getId())
                .nickname(post.getUser().getNickname())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .content(post.getContent())
                .imgUrl(post.getImgUrl())
                .likeCount(likeCount)
                .likeState(check)
                .layout(post.getLayout())
                .build();

        return showInfo;
    }


}
