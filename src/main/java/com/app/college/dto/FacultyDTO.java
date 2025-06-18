package com.app.college.dto;

import lombok.Data;
import java.util.List;

@Data
public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private String designation;
    private String email;
    private String phoneNumber;
    private String specialization;

    private List<String> courseNames;  // Names of courses taught by this faculty
}
