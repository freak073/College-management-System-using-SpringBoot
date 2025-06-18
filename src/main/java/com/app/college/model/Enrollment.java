// package com.app.college.model;

// import java.time.LocalDate;

// import com.fasterxml.jackson.annotation.JsonFormat;

// import jakarta.persistence.*;
// import lombok.*;


// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Enrollment {
    
//     @Id
//     @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
//     private Long enrollmentId;
//     private Long studentId;
//     private Long courseId;

//     @JsonFormat(pattern = "dd-MM-yyyy")
//     private LocalDate enrollmentDate;

   


// }
package com.app.college.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    @JsonIgnoreProperties({"enrollments"})
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    @JsonIgnoreProperties({"enrollments"})
    private Course course;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate enrollmentDate;
}
