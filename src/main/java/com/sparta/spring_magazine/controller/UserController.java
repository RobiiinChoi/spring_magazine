package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.TokenDto;
import com.sparta.spring_magazine.dto.request.LoginRegisterRequestDto;
import com.sparta.spring_magazine.dto.request.LoginRequestDto;
import com.sparta.spring_magazine.jwt.JwtFilter;
import com.sparta.spring_magazine.jwt.TokenProvider;
import com.sparta.spring_magazine.jwt.UserDetailsImpl;
import com.sparta.spring_magazine.service.UserService;
import com.sparta.spring_magazine.util.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserController(UserService userService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder =authenticationManagerBuilder;
    }

    //회원가입 요청
    @PostMapping("/api/register")
    public ResponseEntity userRegister(@Valid @RequestBody LoginRegisterRequestDto requestDto,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails!=null){
            throw new IllegalArgumentException("이미 로그인 되어 있습니다.");
        }
        String msg = userService.userRegister(requestDto);
        return ResponseEntity.ok().body(msg);
    }

    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginRequestDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(loginDto.getUsername(), jwt), httpHeaders, HttpStatus.OK);
    }

}
