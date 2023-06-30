package com.example.cloudsky.service;

import com.example.cloudsky.dto.LoginRequestDto;
import com.example.cloudsky.dto.SignupRequestDto;
import com.example.cloudsky.entity.User;
import com.example.cloudsky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String id = requestDto.getId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUsername();
        String introduction = requestDto.getIntroduction();

        // 회원 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(id, password, username, introduction);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto) {
        String id = requestDto.getId();
        String password = requestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUsername(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
