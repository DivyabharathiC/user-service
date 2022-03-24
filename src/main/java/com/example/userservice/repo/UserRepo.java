package com.example.userservice.repo;


import com.example.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUserId(String userId);


    Object findByEmail(String email);
}
