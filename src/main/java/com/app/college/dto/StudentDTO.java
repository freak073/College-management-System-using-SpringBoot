package com.app.college.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long studentId;
    private String studentName;
    private String email;
    private String course;
    private String branch;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String departmentName; // 💡 Only show department name
}
