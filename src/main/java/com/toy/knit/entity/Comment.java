package com.toy.knit.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(indexes = {@Index(name = "IDX_COMMENT_POST_ID", columnList = "post_id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn
    private Post post;

    @Builder
    public Comment(String content, ZonedDateTime createdAt, ZonedDateTime updatedAt, Post post) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
    }

}
