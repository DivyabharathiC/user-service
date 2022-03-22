package com.example.userservice.service;

import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);

    User getUser(String userId);

    List<User> getAllUsers();

    User updateUserDetails(String userId, UpdateUserDetails user);

    String deleteUser(String userId);




}
