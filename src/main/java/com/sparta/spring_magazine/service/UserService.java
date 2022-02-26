package com.sparta.spring_magazine.service;

import com.sparta.spring_magazine.dto.request.LoginRegisterRequestDto;
import com.sparta.spring_magazine.dto.request.LoginRequestDto;
import com.sparta.spring_magazine.dto.response.LoginResponseDto;
import com.sparta.spring_magazine.jwt.TokenProvider;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public String userRegister(LoginRegisterRequestDto requestDto){
        String username = requestDto.getUsername();
        Optional<User> usernameCheck = userRepository.findByUsername(username);
        if(usernameCheck.isPresent()){
            throw new IllegalArgumentException("해당 아이디는 사용중입니다.");
        }
        Optional<User> nicknameCheck = userRepository.findByUsername(requestDto.getNickname());
        if(nicknameCheck.isPresent()){
            throw new IllegalArgumentException("해당 닉네임은 사용중입니다");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        User userSaved = userRepository.save(User.builder()
                .username(requestDto.getUsername())
                .password(password)
                .nickname(requestDto.getNickname())
                .build());
        String msg = "";
        if(userSaved!=null){
            msg = "success";
        }
        return msg;

//        // 로그인
//        @Transactional
//        public LoginResponseDto login(LoginRequestDto requestDto) {
//            User user = userRepository.findByUsername(requestDto.getUsername())
//                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
//
//            if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
//                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//            }
//            String token = jwtTokenProvider.createToken(Long.toString(user.getId()), user.getUsername(), user.getNickname());
//            LoginResponseDto dto = userRepository.login(requestDto);
//            dto.tokenSet(token);
//
//            return dto;
//        }
    }
}
