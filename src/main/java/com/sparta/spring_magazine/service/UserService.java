package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.LoginRegisterDto;
import com.sparta.spring_magazine.dto.request.LoginRequestDto;
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

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public User userRegister(LoginRegisterDto requestDto){
        loginCheck(requestDto);
        if (userRepository.findOneWithAuthoritiesByUsername(requestDto.getUsername()).orElse(null) != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (userRepository.findOneWithAuthoritiesByNickname(requestDto.getNickname()).orElse(null) != null) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWIthAuthorities(String username){
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    // 로그인
    public void login(LoginRequestDto requestDto){
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
        if (!passwordEncoder.matches(user.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
    }

    public void loginCheck(LoginRegisterDto requestDto){
        // 유저네임 확인
        if(!Pattern.matches("^[a-zA-Z0-9]{3,}$", requestDto.getUsername())) {
            throw new IllegalArgumentException("아이디는 3자 이상, 영어와 숫자만 사용가능합니다 ");
        }

        if(!Pattern.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]{4,}$", requestDto.getNickname())) {
            throw new IllegalArgumentException("닉네임은 4자 이상, 영어와 숫자, 한글만 사용가능합니다 ");
        }

        // 비밀번호 닉네임 포함 확인
        if(requestDto.getPassword().contains(requestDto.getUsername())) {
            throw new IllegalArgumentException("아이디와 비밀번호를 동일하게 설정할 수 없습니다");
        }

        // 비밀번호 길이 확인
        if(requestDto.getPassword().length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4글자 이상만 가능합니다");
        }

        // 비밀번호 일치 확인
        if(!Objects.equals(requestDto.getPassword(), requestDto.getCheckPw())) {
            throw new IllegalArgumentException("비밀번호와 확인용 비밀번호가 다릅니다");
        }
    }
}
