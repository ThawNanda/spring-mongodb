package com.mongodb.demo.service;

import com.mongodb.demo.model.dto.PostDto;
import com.mongodb.demo.model.entity.Post;
import com.mongodb.demo.model.request.PostRequest;
import com.mongodb.demo.model.response.PostResponse;
import org.springframework.data.domain.*;

import java.util.List;

public interface PostService {
    PostDto createPost(PostRequest request, String username);

    List<PostDto> getPostList(String username);

    PostResponse getPostDetail(String id);

    List<Post> findAll();

    Post findPostById(String id);

    List<Post> getPosts(ScrollPosition scrollPosition, Limit limit, Sort sort);

    Page<Post> findAll(Pageable pageable);
}
