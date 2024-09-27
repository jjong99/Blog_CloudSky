package com.example.cloudsky.controller;

import com.example.cloudsky.dto.ApiResponseDto;
import com.example.cloudsky.security.UserDetailsImpl;
import com.example.cloudsky.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dev/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponseDto> denote(@PathVariable(name = "postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.denote(postId, userDetails);
        return ResponseEntity.status(201).body(new ApiResponseDto("좋아요 성공", HttpStatus.CREATED.value()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponseDto> cancel(@PathVariable(name = "postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.cancel(postId, userDetails);
        return ResponseEntity.status(201).body(new ApiResponseDto("좋아요 취소", HttpStatus.CREATED.value()));
    }
}
