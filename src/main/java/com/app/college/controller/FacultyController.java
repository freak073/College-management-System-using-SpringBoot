package com.app.college.controller;

import com.app.college.dto.FacultyDTO;
import com.app.college.dto.FacultyRequestDTO;
import com.app.college.model.Faculty;
import com.app.college.service.FacultyService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public List<FacultyDTO> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public FacultyDTO getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyRequestDTO request) {
        return facultyService.addFaculty(request);
    }

    @PutMapping("/{id}")
    public FacultyDTO updateFaculty(@PathVariable Long id, @RequestBody FacultyRequestDTO request) {
        return facultyService.updateFaculty(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/paginated")
    public Page<Faculty> getPaginatedFaculties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "facultyId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        return facultyService.getPaginatedFaculties(pageable);
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Welcome to Faculty Dashboard");
    }
    
    @GetMapping("/courses")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<String> facultyCourses() {
        return ResponseEntity.ok("Faculty-specific course list or management");
    }
}
