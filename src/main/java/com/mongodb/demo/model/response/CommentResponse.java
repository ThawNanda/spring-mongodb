package com.mongodb.demo.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private String id;
    private String text;
    private UserResponse user;
    private Date createdDate;
}
