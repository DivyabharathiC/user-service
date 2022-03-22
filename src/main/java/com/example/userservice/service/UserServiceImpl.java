package com.example.userservice.service;

import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }
    @Override
    public String deleteUser(String userId) {
        User user = userRepo.findByUserId(userId);
        userRepo.delete(user);
        return "User deleted for id : " + userId;
    }

    @Override
    public User updateUserDetails(String userId, UpdateUserDetails user) {
        User user1=userRepo.findByUserId(userId);
        user1.setEmail(user.getEmail());
        user1.setUserId(user.getUserId());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setEmployeeNumber(user.getEmployeeNumber());
        user1.setGender(user.getGender());
        user1.setFirstName(user.getFirstName());
        user1.setMiddleName(user.getMiddleName());
        user1.setLastName(user.getLastName());
        user1.setBloodGroup(user.getBloodGroup());
        user1.setMartialStatus(user.getMartialStatus());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
        userRepo.save(user1);
        return user1;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
