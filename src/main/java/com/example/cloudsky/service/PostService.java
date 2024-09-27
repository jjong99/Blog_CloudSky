package com.example.cloudsky.service;

import com.example.cloudsky.dto.PostRequestDto;
import com.example.cloudsky.dto.PostResponseDto;
import com.example.cloudsky.entity.Post;
import com.example.cloudsky.entity.User;
import com.example.cloudsky.repository.PostRepository;
import com.example.cloudsky.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 선택한 게시글 하나만 가져오기
    public PostResponseDto getOnePost(Long id) {
        // pathvariable에서 받은 id 값으로 post 찾기
        Post post = findByPostId(id);
        // 해당 post를 responseDto에 담아서 반환
        return new PostResponseDto(post);
    } // 선택한 게시글 조회

    // 게시글 목록 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 생성하기
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        // requestDto로부터 받은 게시글의 제목과 내용을 Post에 넣어줌
        Post post = new Post(requestDto);
        // 위에서 생성한 post에 user를 넣어줌
        post.setUser(user);
        // Repository에 post 저장하기
        postRepository.save(post);
        // 위에서 생성한 post를 ResponseDto에 담아서 반환하기
        return new PostResponseDto(post);
    } // 게시글 생성

    // 게시글 수정
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        // postRepository에서 id로 해당 게시글 찾아오기
        Post post = findByPostId(id);

        // 해당 post의 작성자가 맞는지 확인
        if (user.getUsername().equals(post.getUser().getUsername())) {
            // requestDto로부터 받은 게시글의 제목과 내용으로 해당 post 내용 수정하기
            post.update(requestDto);
            // responseDto에 post 내용을 담아서 반환하기
            return new PostResponseDto(post);
        } else {
            // 해당 post의 작성자가 아니라면 null 반환하기
            return null;
        }
    }

    // 게시글 삭제
    public void deletePost(Long id, User user) {
        // postRepository에서 id로 해당 게시글 찾아오기
        Post post = findByPostId(id);

        // 해당 post의 작성자가 맞는지 확인
        if (user.getUsername().equals(post.getUser().getUsername())) {
            // 맞으면 삭제하기
            postRepository.delete(post);
        }
    }

    // post의 ID 값으로 postRepository 에서 post 찾아서 반환하기
    public Post findByPostId(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found "+ id)
        );
    }
}
