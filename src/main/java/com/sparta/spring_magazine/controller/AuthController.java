package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.request.LoginRequestDto;
import com.sparta.spring_magazine.dto.response.LoginResponseDto;
import com.sparta.spring_magazine.jwt.JwtFilter;
import com.sparta.spring_magazine.jwt.TokenProvider;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.model.responseEntity.LoginSuccess;
import com.sparta.spring_magazine.repository.UserRepository;
import com.sparta.spring_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginSuccess> authorize(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        if(userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("이미 로그인이 되어있습니다.");
        }
        userService.login(loginRequestDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        User user = userService.getMyUserWithAuthorities().get();
        String userId = String.valueOf(user.getId());

        LoginResponseDto responseDto = LoginResponseDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();

        return new ResponseEntity<>(new LoginSuccess("success", "로그인 성공", responseDto, jwt), HttpStatus.OK);
    }
}
