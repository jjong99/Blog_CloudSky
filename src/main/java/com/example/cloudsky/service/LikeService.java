package com.example.cloudsky.service;

import com.example.cloudsky.entity.Like;
import com.example.cloudsky.entity.Post;
import com.example.cloudsky.repository.LikeRepository;
import com.example.cloudsky.repository.PostRepository;
import com.example.cloudsky.security.UserDetailsImpl;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void denote(Long postId, UserDetailsImpl userDetails) {
        Post post = findPost(postId);
        if (userDetails.getUsername().equals(post.getUser().getUsername())) {
            throw new IllegalArgumentException("자신의 게시글에는 좋아요를 누를 수 없습니다.");
        } else {
            if (likeRepository.findByUserAndPost(userDetails, post).isPresent()) {
                throw new DuplicateRequestException("좋아요가 이미 눌러져 있습니다.");
            } else {
                Like like = new Like(userDetails, post);
                likeRepository.save(like);
                postRepository.addLikeCount(post);
            }
        }
    }

    public void cancel(Long postId, UserDetailsImpl userDetails) {
        Post post = findPost(postId);
        if (likeRepository.findByUserAndPost(userDetails, post).isPresent()) {
            Like like = likeRepository.findByUserAndPost(userDetails, post).orElseThrow(() -> new IllegalArgumentException("이 게시글에 좋아요를 누른 적이 없습니다."));
            likeRepository.delete(like);
            postRepository.subLikeCount(post);
        } else {
            throw new IllegalArgumentException("이 게시글에 좋아요를 누른 적이 없습니다.");
        }
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
}
