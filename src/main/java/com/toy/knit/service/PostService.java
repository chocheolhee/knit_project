package com.toy.knit.service;

import com.toy.knit.entity.Post;
import com.toy.knit.repository.post.PostRepository;
import com.toy.knit.request.post.PostCreateAndEdit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 등록
     */
    @Transactional
    public Post createPost(PostCreateAndEdit request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(ZonedDateTime.now())
                .createdAt(ZonedDateTime.now())
                .build();

        postRepository.save(post);

        return post;
    }

    /**
     * 게시글 조회
     * TODO 페이징
     */
    @Transactional
    public Slice<Post> findAll(Pageable page) {
        log.info("page={}", page);
        return postRepository.getList(page);
    }

    /**
     * 게시글 하나 조회
     */
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return post;
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void editPost(Long postId, PostCreateAndEdit request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        post.changeTitleAndContent(request.getTitle(), request.getContent());
        postRepository.save(post);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }
}
