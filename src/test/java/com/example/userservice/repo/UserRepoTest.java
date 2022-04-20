//package com.example.userservice.repo;
//
//import com.example.userservice.enums.Gender;
//import com.example.userservice.model.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//@DataMongoTest
//class UserRepoTest {
//
//    @Autowired
//    UserRepo userRepo;
//
//    @BeforeEach
//    void initUseCase() {
//        User user = createUser();
//        userRepo.save(user);
//    }
//
//    @AfterEach
//    public void destroyByAll() {
//        userRepo.deleteAll();
//    }
//
//
//    @Test
//    public void findUser() {
//        Optional<User> user = userRepo.findById("1");
//        assertEquals("1", user.get().getUserId());
//    }
//
//    @Test
//    public void findUserByEmail() {
//        User user = userRepo.findByEmail(createUser().getEmail());
//        assertEquals("gyarab@maveric-systems.com", user.getEmail());
//    }
//
//    private User createUser() {
//        User user = new User();
//
//        user.setUserId("1");
//        user.setFirstName("John");
//        user.setMiddleName("Babu");
//        user.setLastName("Gyara");
//        user.setPhoneNumber("+919700933932");
//        user.setDateOfBirth(new Date(1992, 9, 9));
//        user.setGender(Gender.MALE);
//        user.setEmployeeId("6969");
//        user.setBloodGroup("A+");
//        user.setEmail("gyarab@maveric-systems.com");
//        user.setPassword("John@123");
//        user.setAddress("Hyderabad");
//
//        return user;
//    }
//
//}