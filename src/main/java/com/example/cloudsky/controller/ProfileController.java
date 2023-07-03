package com.example.cloudsky.controller;

import com.example.cloudsky.dto.ApiResponseDto;
import com.example.cloudsky.dto.PasswordRequestDto;
import com.example.cloudsky.dto.ProfileRequestDto;
import com.example.cloudsky.dto.ProfileResponseDto;
import com.example.cloudsky.security.UserDetailsImpl;
import com.example.cloudsky.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    // 프로필 조회
    @GetMapping("/my-page")
    public ProfileResponseDto getMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getMyPage(userDetails.getUser());
    }

    // 비밀번호 변경
    @PutMapping("/profile/password")
    public ResponseEntity<ApiResponseDto> updatePassword(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PasswordRequestDto password, @RequestBody PasswordRequestDto newpassword) {
        try {
            userService.updatePassword(userDetails, password, newpassword);
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 프로필 수정
    @PutMapping("/profile")
    public ProfileResponseDto updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProfileRequestDto profileRequestDto) {
        return userService.updateProfile(userDetails.getUser(), profileRequestDto);
    }

}
