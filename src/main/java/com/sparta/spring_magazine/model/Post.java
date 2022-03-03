package com.sparta.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.spring_magazine.dto.request.PostRequestDto;
import com.sparta.spring_magazine.dto.request.PostUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

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
    @JoinColumn(name = "user_id")
    @JsonManagedReference
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

    public void update(User user, PostUpdateDto updateDto){
        this.user = user;
        this.content = updateDto.getContent();
        this.imgUrl = updateDto.getImgUrl();
    }
}
