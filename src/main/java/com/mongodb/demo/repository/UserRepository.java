package com.mongodb.demo.repository;

import com.mongodb.demo.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);
}
