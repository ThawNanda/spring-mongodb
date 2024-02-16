package com.mongodb.demo.controller.graphql;

import com.mongodb.demo.model.entity.Post;
import com.mongodb.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostGraphqlController {

    private final PostService postService;

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    List<Post> findAllPosts() {
        return postService.findAll();
    }

    @QueryMapping
    Post findPostById(@Argument String id) {
        return postService.findPostById(id);
    }

    @SchemaMapping(typeName = "Query")
    Page<Post> getPosts(@Argument Integer page, @Argument Integer size) {
        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postService.findAll(pageable);
    }

}
