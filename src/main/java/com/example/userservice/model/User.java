package com.example.userservice.model;

import com.example.userservice.enums.BloodGroup;
import com.example.userservice.enums.Gender;
import com.example.userservice.enums.MartialStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private Gender gender;
    private MartialStatus martialStatus;
    private String employeeNumber;
    private BloodGroup bloodGroup;
    private String email;
    private String password;


}
