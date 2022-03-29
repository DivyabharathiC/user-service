package com.example.userservice.dto;

import com.example.userservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @Id
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private Gender gender;
    private String address;
    private String employeeId;
    private String bloodGroup;
    private String email;
}
