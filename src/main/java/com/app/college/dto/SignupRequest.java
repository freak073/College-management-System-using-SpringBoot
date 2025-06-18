package com.app.college.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String role; // ADMIN, STUDENT, FACULTY
}
