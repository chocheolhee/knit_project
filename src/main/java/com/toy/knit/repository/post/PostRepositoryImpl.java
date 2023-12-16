package com.toy.knit.repository.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.knit.entity.Post;
import com.toy.knit.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Post> getList(Pageable page) {
        List<Post> posts = queryFactory.selectFrom(QPost.post)
                .offset((long) page.getPageNumber() * page.getPageNumber())
                .limit(page.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(
                posts.stream().limit(page.getPageSize()).toList(),
                page,
                posts.size() > page.getPageSize()
        );
    }
}
