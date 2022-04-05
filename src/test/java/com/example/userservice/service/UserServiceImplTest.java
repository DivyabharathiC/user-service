package com.example.userservice.service;

import com.example.userservice.enums.Gender;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {


    @InjectMocks
    UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepo userRepo;

    User RECORD_1 =new User("1","John","Babu","Gyara","9700933932",new Date(),Gender.MALE,"Hyderabad","6969","A+","gyarab@maveric-systems.com","John@123");
    User RECORD_2 =new User("2","Divya","Bharathi","C","90000000000",new Date(),Gender.FEMALE,"Chennai","7000","B+","divya@maveric-systems.com","John@123");


    @Test
    public void testAddUser(){
        User user = createUserToPost();
        Mockito.when(userRepo.save(user)).thenReturn(user);
        assertEquals(user,userRepo.save(user));
    }

    @Test
    public void testUpdateUser(){
        Optional<User> user = getOneUser();
        Mockito.when(userRepo.findById(user.get().getUserId())).thenReturn(user);
       // Mockito.when(userRepo.findByEmail(user.getEmail())).thenReturn(user);

        assertEquals(user,userRepo.findById(user.get().getUserId()));
    }

    @Test
    public void deleteUser() {
        User  user = createUserToPost();
        Mockito.when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
        userRepo.delete(user);
        verify(userRepo,times(1)).delete(user);

    }

    @Test
    public void testGetUsers(){
        List<User> users=getUserList();
        Mockito.when(userRepo.findAll()).thenReturn(users);
        assertEquals(2,userRepo.findAll().size());
    }


    @Test
    public void testGetByEmail(){
        User user = createUserToPost();
        Mockito.when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
        assertEquals(user,userRepo.findByEmail("gyarab@maveric-systems.com"));
    }


    private User createUserToPost() {
        User user = new User();

        user.setUserId("1");
        user.setFirstName("John");
        user.setMiddleName("Babu");
        user.setLastName("Gyara");
        user.setPhoneNumber("+919700933932");
        user.setDateOfBirth(new Date(2021,8,01));
        user.setGender(Gender.MALE);
        user.setEmployeeId("6969");
        user.setBloodGroup("A+");
        user.setEmail("gyarab@maveric-systems.com");
        user.setPassword("John@123");
        user.setAddress("Hyderabad");

        return user;
    }


    private Optional<User> getOneUser() {
        Optional<User> user = Optional.of(new User("1",
                "John",
                "Babu",
                "Gyara",
                "+919700933932",
                new Date(1992, 9, 9),
                Gender.MALE,
                "Hyderabad",
                "6969",
                "A+",
                "gyarab@maveric-systems.com",
                "John@123"));
        return  user;
    }

    private List<User> getUserList() {
        List<User> users = new ArrayList<>();

        users.add(RECORD_1);
        users.add(RECORD_2);
        return users;
    }




}