package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.LoginRegisterRequestDto;
import com.sparta.spring_magazine.dto.request.LoginRequestDto;
import com.sparta.spring_magazine.exception.ErrorException;
import com.sparta.spring_magazine.jwt.TokenProvider;
import com.sparta.spring_magazine.model.Authority;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.UserRepository;
import com.sparta.spring_magazine.util.SecurityUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import static com.sparta.spring_magazine.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public String userRegister(LoginRegisterRequestDto requestDto){
        loginCheck(requestDto);
        String username = requestDto.getUsername();
        Optional<User> usernameCheck = userRepository.findByUsername(username);
        if(usernameCheck.isPresent()){
            throw new ErrorException(USERNAME_CHECK);
        }
        Optional<User> nicknameCheck = userRepository.findByUsername(requestDto.getNickname());
        if(nicknameCheck.isPresent()){
            throw new ErrorException(NICKNAME_CHECK);
        }

        Authority authority =Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        User userSaved = userRepository.save(user);

        String msg = "";
        if(userSaved!=null){
            msg = "success";
        }
        return msg;

//        @Transactional(readOnly = true)
//        public Optional<User> getUserWIthAuthorities(String username){
//            return userRepository.findOneWithAuthoritiesByUsername(username);
//        }
//
//        @Transactional(readOnly = true)
//        public Optional<User> getMyUserWithAuthorities() {
//            return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
//        }
    }


    public void loginCheck(LoginRegisterRequestDto requestDto){
        // 유저네임 확인
        if(!Pattern.matches("^[a-zA-Z0-9]{3,20}$", requestDto.getUsername())) {
            throw new ErrorException(USERNAME_VALIDATE);
        }

        // 비밀번호 닉네임 포함 확인
        if(requestDto.getPassword().contains(requestDto.getUsername())) {
            throw new ErrorException(PASSWORD_INCLUDE_USERNAME);
        }

        // 비밀번호 길이 확인
        if(requestDto.getPassword().length() < 4) {
            throw new ErrorException(PASSWORD_LENGTH);
        }

        // 비밀번호 일치 확인
        if(!Objects.equals(requestDto.getPassword(), requestDto.getCheckPw())) {
            throw new ErrorException(PASSWORD_COINCIDE);
        }
    }
}
