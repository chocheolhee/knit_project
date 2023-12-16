package com.toy.knit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Builder
    public Post(String title, String content, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changeTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = ZonedDateTime.now();
    }
}
