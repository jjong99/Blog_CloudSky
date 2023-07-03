package com.example.cloudsky.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private String realname;
    private String introduction;
}
