package com.sparta.spring_magazine.controller;

import com.sparta.spring_magazine.dto.request.LoginRegisterDto;
import com.sparta.spring_magazine.model.User;
import com.sparta.spring_magazine.model.responseEntity.Success;
import com.sparta.spring_magazine.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입 요청
    @PostMapping("/api/register")
    public ResponseEntity<Success> userRegister(@Valid @RequestBody LoginRegisterDto requestDto){
        if(userService.getMyUserWithAuthorities().isPresent()){
            throw new IllegalArgumentException("이미 로그인이 되어있습니다");
        }
        User user = userService.userRegister(requestDto);
        User newUser = userService.getMyUserWithAuthorities().get();
        return new ResponseEntity<>(new Success("success", "회원가입 성공", newUser.getId()),  HttpStatus.OK);
    }

}
