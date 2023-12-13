package com.toy.knit.controller;

import com.toy.knit.domain.Post;
import com.toy.knit.request.PostCreate;
import com.toy.knit.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts")
    public String getPosts() {
        return "postsController";
    }

    @GetMapping("/api/post/{postId}")
    public Post getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);

        return post;
    }

    @PostMapping("/api/post")
    public void post(@Valid PostCreate request) {
        postService.postCreate(request);
    }

    @PutMapping("/api/post/{postId}")
    public void editPost(@PathVariable Long postId) {
        postService.editPost(postId);
    }

    @DeleteMapping("/api/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
