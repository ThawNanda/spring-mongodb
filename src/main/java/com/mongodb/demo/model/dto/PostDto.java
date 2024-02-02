package com.mongodb.demo.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {

    private String id;
    private String title;
    private String content;
    private String userId;
    private Date createdDate;
}
