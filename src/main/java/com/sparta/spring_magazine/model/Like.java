package com.sparta.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.spring_magazine.dto.request.LikeRequestDto;
import lombok.AccessLevel;
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
    @JsonBackReference
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fkey_user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fkey_post_id"))
    private Post post;

    public Like(LikeRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.post = requestDto.getPost();
    }

}
