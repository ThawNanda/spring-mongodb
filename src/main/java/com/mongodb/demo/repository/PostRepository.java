package com.mongodb.demo.repository;

import com.mongodb.demo.model.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'userId' : ?0 }")
    List<Post> findByUserId(String userId);
}
