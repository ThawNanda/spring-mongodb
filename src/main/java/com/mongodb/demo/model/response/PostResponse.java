package com.mongodb.demo.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private String id;
    private String title;
    private String content;
    private List<CommentResponse> comments;
}
