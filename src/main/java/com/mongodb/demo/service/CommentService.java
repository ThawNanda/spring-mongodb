package com.mongodb.demo.service;

import com.mongodb.demo.model.dto.CommentDto;
import com.mongodb.demo.model.request.CommentRequest;

import java.util.List;

public interface CommentService {
    CommentDto addComment(String username, CommentRequest request);

    List<CommentDto> getComments(String postId);
}
