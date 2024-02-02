package com.mongodb.demo.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "posts")
@Data
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    @Field(name = "userId")
    private String userId;
    private Date createdDate;
}
