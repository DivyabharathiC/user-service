package com.example.userservice.service;

import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);
    String deleteUser(String userId);

    User updateUserDetails(String userId, UpdateUserDetails user);

    List<User> getAllUsers();



}
