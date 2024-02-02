package com.mongodb.demo.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "comments")
@Data
public class Comment {

    @Id
    private String id;
    private String text;
    @Field(name="userId")
    private String userId;
    @Field(name="postId")
    private String postId;
    private Date createdDate;
}
