package com.mongodb.demo.model.request;

import lombok.Data;

@Data
public class CommentRequest {

    private String text;
    private String postId;
}
