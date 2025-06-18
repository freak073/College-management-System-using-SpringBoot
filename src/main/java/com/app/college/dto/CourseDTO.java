package com.app.college.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private String credits;
    private String durations;
    private Long facultyId;
    private String facultyName;
}
