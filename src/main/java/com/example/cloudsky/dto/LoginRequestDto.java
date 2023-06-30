package com.example.cloudsky.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}
// 로그인 할 때 이 모양의 json 형식으로 정보를 입력하시면 됩니다.