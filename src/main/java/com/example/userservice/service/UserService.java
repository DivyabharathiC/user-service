package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);

    User getUser(String userId);

    List<User> getAllUsers();



    String deleteUser(String userId);

    UserDTO userByEmail(String email);

    User updateUserDetails(String userId, User user);
}
