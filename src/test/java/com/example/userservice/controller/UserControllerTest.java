package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.enums.Gender;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    UserController userController;

    @MockBean
    UserRepo userRepo;


    @Autowired
    MockMvc mockMvc;


    UserDTO DTORECORD_1 =new UserDTO("1","John","Babu","Gyara","9700933932",new Date(1992, 9, 9),Gender.MALE,"Hyderabad","6969","A+","gyarab@maveric-systems.com");
    UserDTO DTORECORD_2 =new UserDTO("2","Divya","Bharathi","C","90000000000",new Date(1992, 9, 9),Gender.FEMALE,"Chennai","7000","B+","divya@maveric-systems.com");


    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateUser() throws Exception {
        User response = createUserToPost();
        Mockito.when(userService.createUser(response)).thenReturn(response);
        mockMvc.perform(post("/api/v1/users")
                        .content(asJsonString(response))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void testCreateUserFail() throws Exception {
//        User response = createUserToPost();
//        UserDTO response1 = createOneUserResponse();
//        Mockito.when(userService.createUser(response)).thenReturn(response);
//        mockMvc.perform(post("/api/v1/users")
//                        .content(asJsonString(response))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        assertEquals(response1.getFirstName(),"1" );
//    }

        @Test
        public void getUserById() throws Exception {
            User response = createUserToPost();
            UserDTO response1 = createOneUserResponse();
            Mockito.when(userService.getUser(createUserToPost().getUserId())).thenReturn(response1);
            mockMvc.perform(get("/api/v1/users/1")
                            .content(asJsonString(response))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void testGetAllUsers() throws Exception {
            User response = createUserToPost();
            List<UserDTO> response1 = createUserList();
            Mockito.when(userService.getAllUsers(1, 2)).thenReturn(response1);
            mockMvc.perform(post("/api/v1/users")
                            .content(asJsonString(response))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }


        @Test
        public void testUpdateUser() throws Exception {
            User response = createUserToPost();
            UserDTO response1 = createOneUserResponse();
            Mockito.when(userService.updateUser(response.getUserId(), response)).thenReturn(response);
            mockMvc.perform(put("/api/v1/users/1")
                            .content(asJsonString(response1))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void testDeleteUserById() throws Exception {
            User response = createUserToPost();
            Mockito.when(userService.deleteUser(response.getUserId())).thenReturn(String.valueOf(true));
            mockMvc.perform(delete("/api/v1/users/1")
                            .content(asJsonString(response))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void testGetUserByEmail() throws Exception {
            User response = createUserToPost();

            Mockito.when(userService.userByEmail(response.getEmail())).thenReturn(response);
            mockMvc.perform(get("/api/v1/users/getUserByEmail/gyarab@maveric-systems.com")
                            .content(asJsonString(response))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        private User createUserToPost() {
            User user = new User();
            user.setUserId("1");
            user.setFirstName("John");
            user.setMiddleName("Babu");
            user.setLastName("Gyara");
            user.setPhoneNumber("9700933932");
            user.setDateOfBirth(new Date(1992, 9, 9));
            user.setGender(Gender.MALE);
            user.setEmployeeId("6969");
            user.setBloodGroup("A+");
            user.setEmail("gyarab@maveric-systems.com");
            user.setPassword("John@123");
            user.setAddress("Hyderabad");

            return user;
        }
        private UserDTO createOneUserResponse() {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId("1");
            userDTO.setFirstName("John");
            userDTO.setMiddleName("Babu");
            userDTO.setLastName("Gyara");
            userDTO.setPhoneNumber("9700933932");
            userDTO.setDateOfBirth(new Date(1992, 9, 9));
            userDTO.setGender(Gender.MALE);
            userDTO.setEmployeeId("6969");
            userDTO.setBloodGroup("A+");
            userDTO.setEmail("gyarab@maveric-systems.com");
          //  userDTO.setPassword("John@123");
            userDTO.setAddress("Delhi");
            return userDTO;
        }

        private List<UserDTO> createUserList() {
            List<UserDTO> users = new ArrayList<>();

            users.add(DTORECORD_1);
            users.add(DTORECORD_2);
            return users;
        }

    }




