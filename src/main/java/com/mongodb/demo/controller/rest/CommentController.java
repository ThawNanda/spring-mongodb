package com.mongodb.demo.controller.rest;

import com.mongodb.demo.model.request.CommentRequest;
import com.mongodb.demo.security.CurrentUser;
import com.mongodb.demo.security.UserPrincipal;
import com.mongodb.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addComment(@CurrentUser UserPrincipal currentUser, @RequestBody CommentRequest request){
        return new ResponseEntity<>(commentService.addComment(currentUser.getUsername(),request), HttpStatus.OK);
    }
}
