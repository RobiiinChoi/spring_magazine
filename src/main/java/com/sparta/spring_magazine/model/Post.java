package com.sparta.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.spring_magazine.dto.request.PostRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="posts")
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Layout layout;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<>();

    public Post(User user, PostRequestDto requestDto) {
        this.user = user;
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.layout = requestDto.getLayout();
    }
}
