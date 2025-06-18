package com.app.college.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private LocalDate enrollmentDate;
}
