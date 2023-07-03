package com.example.cloudsky.controller;

import com.example.cloudsky.entity.Post;
import com.example.cloudsky.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록 조회
    @GetMapping("/dev")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 게시글 작성
    @PostMapping("/dev/post")
    public Post createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    // 게시글 수정
    @PutMapping("/dev/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }

    // 게시글 삭제
    @DeleteMapping("/dev/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
