package com.example.userservice.controller;

import com.example.userservice.enums.Gender;
import com.example.userservice.enums.MartialStatus;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createUser() throws Exception {
        User user= createNewUser();
        Mockito.when(userService.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/users/user-new")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private User createNewUser() {
        User user=new User();
        user.setUserId("1");
        user.setFirstName("John");
        user.setMiddleName("Babu");
        user.setLastName("Gyara");
        user.setDateOfBirth(new Date());
        user.setGender(Gender.MALE);
        user.setMartialStatus(MartialStatus.SINGLE);
        user.setEmployeeNumber("6969");
        user.setEmail("gyarab@maveric-systems.com");
        user.setPassword("1234567");
        return user;
    }
}