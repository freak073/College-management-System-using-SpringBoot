package com.app.college.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private String studentName;
    private String email;
    private String course;
    private String branch;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Long departmentId;  // ✅ Just the ID, not full object
}
