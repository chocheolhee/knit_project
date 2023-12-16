package com.toy.knit.controller;

import com.toy.knit.request.comment.CommentCreateAndEdit;
import com.toy.knit.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public void write(@PathVariable(name = "postId") Long postId, @RequestBody @Valid CommentCreateAndEdit request) {
        commentService.createComment(postId, request);
    }

    @DeleteMapping("/api/comments/{commentId}/delete")
    public void delete(@PathVariable(name = "commentId") Long commentId) {
        commentService.delete(commentId);
    }
}
