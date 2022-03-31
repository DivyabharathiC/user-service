package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.EmailAlreadyExistsException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.userservice.constant.Constant.EmailAlreadyExists;
import static com.example.userservice.constant.Constant.UserNotFound;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        User user1 = userRepo.findByEmail(user.getEmail());
        if(user1!=null){
            throw new EmailAlreadyExistsException( EmailAlreadyExists );
        }
        return userRepo.save(user);
    }

    @Override
    public UserDTO getUser(String userId) {
        try {
        User user= userRepo.findById(userId).get();
            UserDTO userDTO = new UserDTO();
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
        catch(Exception e){
            throw new UserNotFoundException( UserNotFound + userId);
        }
    }


    @Override
    public List<UserDTO> getAllUsers(Integer page, Integer size) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(10);
        Pageable paging = PageRequest.of(page, size);
        Page<User> users = userRepo.findAll(paging);
        if(users.isEmpty()){
            throw new UserNotFoundException( UserNotFound + users);
        }
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
    public User updateUser(String userId, User user) {
        if(userRepo.findById(userId).isPresent()){

            User user1 = userRepo.findByEmail(user.getEmail());
            if(user1!=null && user1.getUserId()!=userId){
                throw new EmailAlreadyExistsException(EmailAlreadyExists);
            }
            user.setUserId(userId);
            return this.userRepo.save(user);
        }
        else{
            throw new UserNotFoundException(UserNotFound);
        }

    }

    @Override
    public String deleteUser(String userId) {
        try {
        User user = userRepo.findByUserId(userId);
        userRepo.delete(user);
        return "User deleted for id : " + userId;
        } catch (Exception e) {
            throw new UserNotFoundException(UserNotFound);
        }
    }

    @Override
    public UserDTO userByEmail(String email) {
        try {
            User user = userRepo.findByEmail(email);
            UserDTO userDTO = new UserDTO(user.getUserId(),
                    user.getFirstName(), user.getMiddleName(), user.getLastName(),
                    user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(),
                    user.getEmployeeId(), user.getBloodGroup(), user.getAddress(), user.getEmail());
            return userDTO;
        } catch (Exception e) {
            throw new UserNotFoundException(UserNotFound + email);
        }
    }
}
