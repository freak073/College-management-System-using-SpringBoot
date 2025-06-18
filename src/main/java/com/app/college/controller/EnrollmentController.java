package com.app.college.controller;

import com.app.college.dto.EnrollmentDTO;
import com.app.college.dto.EnrollmentRequestDTO;
import com.app.college.model.Enrollment;
import com.app.college.model.Faculty;
import com.app.college.service.EnrollmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enrollStudent(
            @RequestBody EnrollmentRequestDTO request) {
        return ResponseEntity.ok(
                enrollmentService.enrollStudent(
                        request.getStudentId(),
                        request.getCourseId(),
                        request.getEnrollmentDate() // pass it along
                ));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(
            @PathVariable("id") Long enrollmentId,
            @RequestBody EnrollmentRequestDTO request) {

        return ResponseEntity.ok(
                enrollmentService.updateEnrollment(
                        enrollmentId,
                        request.getStudentId(),
                        request.getCourseId(),
                        request.getEnrollmentDate()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/paginated")
    public Page<Enrollment> getPaginatedEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "EnrollmentId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        return enrollmentService.getPaginatedEnrollments(pageable);
    }
}
