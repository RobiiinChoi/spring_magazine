package com.sparta.spring_magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    protected User() {}

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated")
    @ColumnDefault("true")
    private boolean activated = true;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Like> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public User(String username, String password, String nickname, boolean activated, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.activated = activated;
        this.authorities = authorities;
    }
}
