package com.app.college.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String credits;
    private String durations;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonIgnoreProperties("courses")
    private Faculty faculty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private List<Enrollment> enrollments = new ArrayList<>();
}

