package com.example.cloudsky.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
    private String msg;
    private Integer statusCode;

    public ApiResponseDto(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    // PostDto
    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDto {
        private String title;
        private String content;
    }

    // UserDto
    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserDto {
        private String username;
        private String password;
        private String email;
    }
}
