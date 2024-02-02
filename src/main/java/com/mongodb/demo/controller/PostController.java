package com.mongodb.demo.controller;

import com.mongodb.demo.model.dto.PostDto;
import com.mongodb.demo.model.request.PostRequest;
import com.mongodb.demo.security.CurrentUser;
import com.mongodb.demo.security.UserPrincipal;
import com.mongodb.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDto> createPost(@CurrentUser UserPrincipal currentUser,
                                              @RequestBody PostRequest request) {
        log.info("currentUser - > " + currentUser.getUsername());

        return new ResponseEntity<>(postService.createPost(request, currentUser.getUsername()), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getPostList(@CurrentUser UserPrincipal currentUser) {
        return new ResponseEntity<>(postService.getPostList(currentUser.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getPostDetail(@CurrentUser UserPrincipal currentUser,
                                           @PathVariable String id) {
        return new ResponseEntity<>(postService.getPostDetail(id), HttpStatus.OK);
    }

}
