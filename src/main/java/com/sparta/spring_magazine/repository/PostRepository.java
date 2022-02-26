package com.sparta.spring_magazine.repository;

import com.sparta.spring_magazine.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select distinct p from Post p join fetch p.user order by p.createdAt desc")
    List<Post> findAllFetched(Pageable pageable);
}
