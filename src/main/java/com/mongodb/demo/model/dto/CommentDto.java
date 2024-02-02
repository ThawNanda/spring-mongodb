package com.mongodb.demo.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private String id;
    private String text;
    private String userId;
    private String postId;
    private Date createdDate;
}
