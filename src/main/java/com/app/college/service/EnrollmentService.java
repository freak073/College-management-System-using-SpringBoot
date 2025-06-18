package com.app.college.service;

import com.app.college.dto.EnrollmentDTO;
import com.app.college.model.Course;
import com.app.college.model.Enrollment;
import com.app.college.model.Faculty;
import com.app.college.model.Student;
import com.app.college.repository.CourseRepository;
import com.app.college.repository.EnrollmentRepository;
import com.app.college.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentDTO enrollStudent(Long studentId, Long courseId, LocalDate enrollmentDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(
                enrollmentDate != null ? enrollmentDate : LocalDate.now());

        return toDTO(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return toDTO(enrollment);
    }

    public EnrollmentDTO updateEnrollment(Long enrollmentId, Long studentId, Long courseId, LocalDate date) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        // ✅ Update the enrollment date if provided, otherwise keep existing
        enrollment.setEnrollmentDate(date != null ? date : enrollment.getEnrollmentDate());

        return toDTO(enrollmentRepository.save(enrollment));
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found");
        }
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDTO toDTO(Enrollment e) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setEnrollmentId(e.getEnrollmentId());
        dto.setEnrollmentDate(e.getEnrollmentDate());
        dto.setStudentId(e.getStudent().getStudentId());
        dto.setStudentName(e.getStudent().getStudentName());
        dto.setCourseId(e.getCourse().getCourseId());
        dto.setCourseName(e.getCourse().getCourseName());
        return dto;
    }

    public Page<Enrollment> getPaginatedEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }

}
