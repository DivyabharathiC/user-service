package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UserDTO getUser(String userId) {
        User user= userRepo.findById(userId).get();
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmail(user.getEmail());
        userDTO.setGender(user.getGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmployeeId(user.getEmployeeId());
        userDTO.setBloodGroup(user.getBloodGroup());
        userDTO.setEmail(user.getEmail());
        return  userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getUserId(), user.getFirstName(), user.getMiddleName(),
                    user.getLastName(), user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(),
                    user.getAddress(), user.getEmployeeId(), user.getBloodGroup(), user.getEmail());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public User updateUser(String userId, UserDTO userDTO) {
        User user=userRepo.findByUserId(userId);
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setAddress(userDTO.getAddress());
        user.setEmployeeId(userDTO.getEmployeeId());
        user.setBloodGroup(userDTO.getBloodGroup());
        user.setEmail(userDTO.getEmail());

        userRepo.save(user);
        return user;
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
        UserDTO userDTO = new UserDTO(user.getUserId(),user.getFirstName(),user.getMiddleName(),user.getLastName(),user.getPhoneNumber(),user.getDateOfBirth(),user.getGender(),user.getEmployeeId(),user.getBloodGroup(),user.getAddress(),user.getEmail());
        return  userDTO;
    }
}
