package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.PostRequestDto;
import com.sparta.spring_magazine.dto.request.PostUpdateDto;
import com.sparta.spring_magazine.model.Authority;
import com.sparta.spring_magazine.model.Layout;
import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.PostRepository;
import com.sparta.spring_magazine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void resetRepository() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글쓰기")
    void writePost() throws  Exception {
        User user = userRegister();
        PostRequestDto requestDto = createPostDto(user);

        Post post = new Post(user, requestDto);
        Post save = postRepository.save(post);

        assertEquals(user.getUsername(), save.getUser().getUsername());
        assertEquals(requestDto.getImgUrl(), save.getImgUrl());
        assertEquals(requestDto.getContent(), save.getContent());
        assertEquals(requestDto.getLayout(), save.getLayout());
    }

    @Test
    @DisplayName("글삭제")
    void deletePost() throws  Exception {
        User user = userRegister();
        PostRequestDto requestDto = createPostDto(user);

        Post post = new Post(user, requestDto);
        Post save = postRepository.save(post);

        assertEquals(user.getUsername(), save.getUser().getUsername());
        assertEquals(requestDto.getImgUrl(), save.getImgUrl());
        assertEquals(requestDto.getContent(), save.getContent());
        assertEquals(requestDto.getLayout(), save.getLayout());
    }

    @Test
    @DisplayName("작성자 체크")
    void deleteCheck() throws Exception {
        User user1 = new User();
        user1.setId(-1L);

        User user2 = userRegister();
        Post post = writePost(user2);

        Exception e = assertThrows(Exception.class, () ->
                postService.deletePost(post.getId(), user1));

        assertEquals("글 작성자만 삭제 가능합니다.", e.getMessage());


    }

    @Test
    @DisplayName("수정 체크")
    void upcateCheck() throws Exception{
        User user2 = userRegister();
        Post post = writePost(user2);
        PostUpdateDto updateDto = postUpdateDto(user2);

        Exception e = assertThrows(Exception.class, () ->
                postService.updatePost(post.getId(), updateDto, user2));

        assertEquals("글, 사진을 수정해주세요", e.getMessage());

    }

    private PostUpdateDto postUpdateDto(User user){
        String imgUrl = null;
        String content = "3시까지 할거야";
        return new PostUpdateDto(imgUrl, content);
    }

    private User userRegister(){
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username("yaya")
                .password("12345")
                .nickname("안녕하세요")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        userRepository.save(user);
        return user;
    }


    private Post writePost(User user2) {
        PostRequestDto postRequestDto = createPostDto(user2);
        Post post = new Post(user2, postRequestDto);
        postRepository.save(post);
        return post;
    }

    private PostRequestDto createPostDto(User user){
        String imgUrl = "3pm.jpg";
        String content = "3시까지 할거야";
        Layout layout = Layout.valueOf("BOTTOM");
        return new PostRequestDto(imgUrl, content, layout);
    }

}
