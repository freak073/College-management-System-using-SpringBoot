package com.app.college.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    private String facultyName;
    private String designation;
    private String email;
    private String phoneNumber;
    private String specialization;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("faculty")
    private List<Course> courses = new ArrayList<>();
}
