package com.example.userservice.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private HttpStatus status;
    private List<String> error;

    private String path;
}

