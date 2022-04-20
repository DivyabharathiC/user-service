package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO createUser(User user);

    UserDTO getUser(String userId);

  

    String deleteUser(String userId);

    User userByEmail(String email);

    UserDTO updateUser(String userId, User user);

    List<UserDTO> getAllUsers(Integer page, Integer size);
}
