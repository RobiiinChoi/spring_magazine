package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.LoginRegisterDto;
import com.sparta.spring_magazine.model.Authority;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("아이디 중복확인")
    void duplicatedUserId(){
        User testUser = userRegister("newone");
        LoginRegisterDto registerDto = new LoginRegisterDto("newone", "testPassword", "testPassword", "testnickname");

        Exception e = assertThrows(Exception.class, () ->
                userService.userRegister(registerDto));

        assertEquals("이미 존재하는 이메일입니다.", e.getMessage());
    }

    @Test
    @DisplayName("비밀번호 체크")
    void duplicationPassword(){
        User testUser = userRegister("newone");
        LoginRegisterDto registerDto = new LoginRegisterDto("newone", "testPassword", "passwordcheck", "testnickname");

        Exception e = assertThrows(Exception.class, () ->
                userService.userRegister(registerDto));

        assertEquals("비밀번호와 확인용 비밀번호가 다릅니다", e.getMessage());
    }

    private User userRegister(String username){
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(username)
                .password("12345")
                .nickname("안녕하세요")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        userRepository.save(user);
        return user;
    }
}
