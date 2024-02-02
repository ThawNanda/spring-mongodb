package com.mongodb.demo.model.request;

import lombok.Data;

@Data
public class PostRequest {

    private String title;
    private String content;
}
