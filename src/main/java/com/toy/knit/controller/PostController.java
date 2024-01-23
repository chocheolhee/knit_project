package com.toy.knit.controller;

import com.toy.knit.entity.Post;
import com.toy.knit.request.post.PostCreateAndEdit;
import com.toy.knit.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/post/{postId}")
    public Post getPost(@PathVariable(name = "postId") Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/api/posts")
    public Slice<Post> getPosts(Pageable page) {
        Slice<Post> posts = postService.findAll(page);

        return posts;
    }

    @PostMapping("/api/post")
    public Post write(@RequestBody @Valid PostCreateAndEdit request) {
        return postService.createPost(request);
    }

    @PutMapping("/api/post/{postId}")
    public void edit(@PathVariable(name = "postId") Long postId, @RequestBody PostCreateAndEdit request) {
        postService.editPost(postId, request);
    }

    @DeleteMapping("/api/post/{postId}")
    public void delete(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
    }
}
