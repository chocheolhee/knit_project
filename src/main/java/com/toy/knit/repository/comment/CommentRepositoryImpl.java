package com.toy.knit.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.knit.entity.Comment;
import com.toy.knit.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Comment> findSliceByPostId(Long postId, Pageable page) {
        List<Comment> comments = queryFactory.select(QComment.comment)
                .from(QComment.comment)
                .where(QComment.comment.post.id.eq(postId))
                .offset((long) page.getPageNumber() * page.getPageNumber())
                .limit(page.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(
                comments.stream().limit(page.getPageSize()).toList(),
                page,
                comments.size() > page.getPageSize()
        );
    }
}
