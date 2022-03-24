package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUserDetails(String userId, User user) {
        User user1=userRepo.findByUserId(userId);
        user1.setUserId(user.getUserId());
        user1.setFirstName(user.getFirstName());
        user1.setMiddleName(user.getMiddleName());
        user1.setLastName(user.getLastName());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setEmployeeId(user.getEmployeeId());
        user1.setGender(user.getGender());
        user1.setBloodGroup(user.getBloodGroup());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
        userRepo.save(user1);
        return user1;
    }

    @Override
    public String deleteUser(String userId) {
        User user = userRepo.findByUserId(userId);
        userRepo.delete(user);
        return "User deleted for id : " + userId;
    }

    @Override
    public UserDTO userByEmail(String email) {
        User user = (User) userRepo.findByEmail(email);
        UserDTO userDTO = new UserDTO(user.getUserId(),user.getFirstName(),user.getMiddleName(),user.getLastName(),user.getPhoneNumber(),user.getDateOfBirth(),user.getGender(),user.getEmployeeId(),user.getBloodGroup(),user.getAddress(),user.getEmail(),user.getPassword());
        return  userDTO;
    }
}
