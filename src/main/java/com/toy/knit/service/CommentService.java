package com.toy.knit.service;

import com.toy.knit.entity.Comment;
import com.toy.knit.entity.Post;
import com.toy.knit.repository.comment.CommentRepository;
import com.toy.knit.repository.post.PostRepository;
import com.toy.knit.request.comment.CommentCreateAndEdit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long postId, CommentCreateAndEdit request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .post(post)
                .content(request.getContent())
                .createdAt(ZonedDateTime.now())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다"));
        commentRepository.delete(comment);
    }
}
