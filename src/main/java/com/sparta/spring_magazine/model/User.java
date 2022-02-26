package com.sparta.spring_magazine.model;

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
@Builder
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

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

}
