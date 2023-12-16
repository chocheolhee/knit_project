package com.toy.knit.repository.post;

import com.toy.knit.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostRepositoryCustom {

    Slice<Post> getList(Pageable page);
}
