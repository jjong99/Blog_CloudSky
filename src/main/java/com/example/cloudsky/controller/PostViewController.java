package com.example.cloudsky.controller;

import com.example.cloudsky.dto.PostResponseDto;
import com.example.cloudsky.entity.Post;
import com.example.cloudsky.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    private final PostService postService;

    // post 내용 반환
    @GetMapping("/dev/post/{id}")
    public String getOnePost(@PathVariable Long id, Model model) {
        Post post = postService.findByPostId(id);
        model.addAttribute("post", new PostResponseDto(post));

        return "post"; // post.html 뷰 조회
    }
}
