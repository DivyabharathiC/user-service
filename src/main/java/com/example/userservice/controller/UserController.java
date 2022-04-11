package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        logger.info("Starting of user post request from userDTO application");
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId){
        logger.info("Starting of user get by id request from user application");
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size){
        logger.info("Starting of user get all request from user application");
        return new ResponseEntity<>(userService.getAllUsers(page, size), HttpStatus.OK);
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        logger.info("Starting of user delete  request from user application");
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @PutMapping(path="/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        logger.info("Starting of user Put request from user application");
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<User> userByEmail(@PathVariable("email") String email){
        logger.info("Starting of user getUserByEmail request from user application");
        return new ResponseEntity<>(userService.userByEmail(email),HttpStatus.ACCEPTED);
    }

}
