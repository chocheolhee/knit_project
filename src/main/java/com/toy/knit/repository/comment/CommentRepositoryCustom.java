package com.toy.knit.repository.comment;

import com.toy.knit.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentRepositoryCustom {
    Slice<Comment> findSliceByPostId(Long postId, Pageable page);
}
