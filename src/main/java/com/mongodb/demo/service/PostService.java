package com.mongodb.demo.service;

import com.mongodb.demo.model.dto.PostDto;
import com.mongodb.demo.model.request.PostRequest;
import com.mongodb.demo.model.response.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostRequest request, String username);

    List<PostDto> getPostList(String username);

    PostResponse getPostDetail(String id);
}
