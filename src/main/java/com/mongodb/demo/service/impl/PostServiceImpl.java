package com.mongodb.demo.service.impl;

import com.mongodb.demo.model.dto.PostDto;
import com.mongodb.demo.model.entity.Post;
import com.mongodb.demo.model.entity.User;
import com.mongodb.demo.model.mapper.PostDetailMapper;
import com.mongodb.demo.model.mapper.PostMapper;
import com.mongodb.demo.model.request.PostRequest;
import com.mongodb.demo.model.response.PostResponse;
import com.mongodb.demo.repository.PostRepository;
import com.mongodb.demo.repository.UserRepository;
import com.mongodb.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostMapper postMapper;

    private final PostDetailMapper postDetailMapper;

    private final MongoTemplate mongoTemplate;

    @Override
    public PostDto createPost(PostRequest request, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = new Post();
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setUserId(user.getId());
        Post savedPost = postRepository.save(post);
        return postMapper.toPostDto(savedPost);
    }

    @Override
    public List<PostDto> getPostList(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        List<Post> posts = postRepository.findByUserId(user.getId());
        return posts.stream().map(postMapper::toPostDto).toList();
    }

    @Override
    public PostResponse getPostDetail(String id) {
        return postDetailMapper.toPostDetailResponse(postRepository.findById(id).orElseThrow());
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getPosts(ScrollPosition scrollPosition, Limit limit, Sort sort) {

        Query query = new Query()
                .with(Sort.by(Sort.Order.desc("id")))
                .skip(10)
                .limit(limit);

        return mongoTemplate.find(query, Post.class);

    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
