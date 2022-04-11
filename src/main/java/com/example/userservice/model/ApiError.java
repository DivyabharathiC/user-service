package com.example.userservice.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError {

    private String path;
    private String code;
    private String message;
}

