package com.sparta.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.spring_magazine.dto.request.LikeRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Like(LikeRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.post = requestDto.getPost();
    }

}
