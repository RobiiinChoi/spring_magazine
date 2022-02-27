package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.LikeRequestDto;
import com.sparta.spring_magazine.model.Like;
import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public int like(User user, Post post){
        Optional<Like> like = likeRepository.findByUserAndPost(user, Optional.ofNullable(post));

        if (like.isPresent()){
            Long likeId = like.get().getId();
            likeRepository.deleteById(likeId);
            return -1;
        } else {
            LikeRequestDto responseDto = new LikeRequestDto(user, post);
            Like deleteLike = new Like(responseDto);
            Like save = likeRepository.save(deleteLike);
            return 1;
        }

    }
}
