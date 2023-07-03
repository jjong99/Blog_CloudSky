package com.example.cloudsky.controller;

import com.example.cloudsky.dto.PostRequestDto;
import com.example.cloudsky.dto.PostResponseDto;
import com.example.cloudsky.security.UserDetailsImpl;
import com.example.cloudsky.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/dev")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }


    // 선택 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getOnePost(@PathVariable Long id) {
        return postService.getOnePost(id);
    }
//     선택 게시글 조회
//    @GetMapping("/post/{id}")
//    public PostResponseDto getOnePost(@PathVariable Long id) {
//        return postService.getOnePost(id);
//    }

    // 게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(requestDto, userDetails.getUser());
    }
    // 게시글 수정
    @Transactional
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, requestDto, userDetails.getUser());
    }
    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUser());
    }
}