package com.app.college.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDTO {
    private Long studentId;
    private Long courseId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

}

