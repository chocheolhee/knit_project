package com.toy.knit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageKey;
    private String originalName;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
    private Post post;

    @Builder
    public Image(String imageKey, String originalName, ZonedDateTime createdAt, ZonedDateTime updatedAt, Post post) {
        this.imageKey = imageKey;
        this.originalName = originalName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
    }
}
