package com.example.cloudsky.service;

import com.example.cloudsky.dto.PasswordRequestDto;
import com.example.cloudsky.dto.ProfileRequestDto;
import com.example.cloudsky.dto.ProfileResponseDto;
import com.example.cloudsky.dto.SignupRequestDto;
import com.example.cloudsky.entity.User;
import com.example.cloudsky.entity.UserRoleEnum;
import com.example.cloudsky.repository.UserRepository;
import com.example.cloudsky.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String realname = requestDto.getRealname();
        String introduction = requestDto.getIntroduction();
        UserRoleEnum role = UserRoleEnum.USER;

        // 회원 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username, password, realname, introduction, role);
        userRepository.save(user);
    }


    public ProfileResponseDto getMyPage(User user) {
        return new ProfileResponseDto(user);
    }

    // 프로필 수정
    @Transactional
    public ProfileResponseDto updateProfile(User user, ProfileRequestDto profileRequestDto) {
        user.setRealname(profileRequestDto.getNickname());
        user.setIntroduction(profileRequestDto.getIntroduction());
        return new ProfileResponseDto(user);
    }

    // 비밀번호 변경
    public void updatePassword(UserDetailsImpl userDetails, PasswordRequestDto password, PasswordRequestDto newpassword) {
        User user = userDetails.getUser();

        if(!passwordEncoder.matches(password.getPassword(), user.getPassword())) {
            throw new RejectedExecutionException();
        }
        user.setPassword(newpassword.getPassword());
    }
}
