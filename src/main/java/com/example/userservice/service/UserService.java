package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);

    UserDTO getUser(String userId);

  

    String deleteUser(String userId);

    User userByEmail(String email);

    User updateUser(String userId, User user);

    List<UserDTO> getAllUsers(Integer page, Integer size);
}
