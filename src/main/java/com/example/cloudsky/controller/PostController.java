package com.example.cloudsky.controller;

import com.example.cloudsky.service.PostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
}
