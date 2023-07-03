package com.example.cloudsky.dto;

import com.example.cloudsky.entity.Post;
import com.example.cloudsky.entity.User;

import java.util.List;

public class ProfileResponseDto {
    private String username;
    // private String password;
    private String nickname;
    private String introduction;

    private List<Post> postList;

    public ProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getRealname();
        this.introduction = user.getIntroduction();
        this.postList = user.getPostList();
    }
}
