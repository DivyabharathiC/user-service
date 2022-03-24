package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.UpdateUserDetails;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @PutMapping(path="/{userId}")
    public ResponseEntity<User> updateUserDetails(@PathVariable("userId") String userId,  @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUserDetails(userId, user), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> userByEmail(@PathVariable("email") String email){
        return new ResponseEntity<UserDTO>(userService.userByEmail(email),HttpStatus.ACCEPTED);
    }

}
