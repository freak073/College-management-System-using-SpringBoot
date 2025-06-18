package com.app.college.dto;

import lombok.Data;

@Data
public class CourseRequestDTO {
    private String courseName;
    private String credits;
    private String durations;
    private Long facultyId;
}
