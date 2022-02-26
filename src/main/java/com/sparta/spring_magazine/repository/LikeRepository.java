package com.sparta.spring_magazine.repository;

import com.sparta.spring_magazine.model.Like;
import com.sparta.spring_magazine.model.Post;
import com.sparta.spring_magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Optional<Post> post);
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
