package com.example.userservice.repo;


import com.example.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUserId(String userId);
}
