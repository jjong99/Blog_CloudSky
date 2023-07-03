package com.example.cloudsky.controller;

import com.example.cloudsky.dto.UserDto;
import com.example.cloudsky.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/dev/user/signup")
    public void signup(@RequestBody UserDto userDto) {
        userService.signup(userDto);
    }

    // 로그인
    @PostMapping("/dev/user/login")
    public String login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    // 회원정보 수정
    @PutMapping("/dev/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }
}
