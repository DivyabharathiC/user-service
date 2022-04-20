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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.userservice.constant.Constant.EMAIL_ALREADY_EXISTS;
import static com.example.userservice.constant.Constant.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDTO createUser(User user) {
        User user1 = userRepo.findByEmail(user.getEmail());
        if(user1 !=null){
            throw new EmailAlreadyExistsException( EMAIL_ALREADY_EXISTS );
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
        UserDTO userDTO= new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmployeeId(user.getEmployeeId());
        userDTO.setBloodGroup(user.getBloodGroup());
        userDTO.setGender(user.getGender());
        return  userDTO;

    }

    @Override
    public UserDTO getUser(String userId) {
        try {
            Optional<User> optional = (userRepo.findById(userId));
            if (optional.isPresent()) {
                User user=optional.get();
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
                return userDTO;
            } else {
                throw new UserNotFoundException(USER_NOT_FOUND + userId);
            }
        } catch (Exception e) {
            throw new UserNotFoundException(USER_NOT_FOUND + userId);
        }
    }


        @Override
    public List<UserDTO> getAllUsers(Integer page, Integer size) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(10);
        Pageable paging = PageRequest.of(page, size);
        Page<User> users = userRepo.findAll(paging);
        if(users.isEmpty()){
            throw new UserNotFoundException( USER_NOT_FOUND + users);
        }
        List<UserDTO> userRespons = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getUserId(), user.getFirstName(), user.getMiddleName(),
                    user.getLastName(), user.getPhoneNumber(), user.getDateOfBirth(), user.getGender(),
                    user.getAddress(), user.getEmployeeId(), user.getBloodGroup(), user.getEmail());
            userRespons.add(userDTO);
        }
        return userRespons;
    }

    @Override
    public UserDTO updateUser(String userId, User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if(userRepo.findById(userId).isPresent()){

            User user1 = userRepo.findByEmail(user.getEmail());
            if(user1 !=null && user1.getUserId()!=userId){
                throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
            }
            user.setUserId(userId);
            userRepo.save(user);
            UserDTO userDTO= new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setMiddleName(user.getMiddleName());
            userDTO.setLastName(user.getLastName());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setEmail(user.getEmail());
            userDTO.setAddress(user.getAddress());
            userDTO.setDateOfBirth(user.getDateOfBirth());
            userDTO.setEmployeeId(user.getEmployeeId());
            userDTO.setBloodGroup(user.getBloodGroup());
            userDTO.setGender(user.getGender());
            return  userDTO;
        }
        else{
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

    }

    @Override
    public String deleteUser(String userId) {
        try {
        User user = userRepo.findByUserId(userId);
        userRepo.delete(user);
        return "User deleted for id : " + userId;
        } catch (Exception e) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
    }

    @Override
    public User userByEmail(String email) {
        if (userRepo.findByEmail(email)!=null){

            return userRepo.findByEmail(email);
        }else{

            throw new UserNotFoundException(USER_NOT_FOUND + email);
        }
    }
}
