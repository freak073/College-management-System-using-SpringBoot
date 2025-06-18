package com.app.college.service;

import com.app.college.dto.FacultyDTO;
import com.app.college.dto.FacultyRequestDTO;
import com.app.college.model.Faculty;
import com.app.college.repository.FacultyRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRespository facultyRespository;

    public List<FacultyDTO> getAllFaculties() {
        return facultyRespository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFacultyById(Long id) {
        return facultyRespository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    public FacultyDTO addFaculty(FacultyRequestDTO request) {
        Faculty faculty = new Faculty();
        faculty.setFacultyName(request.getFacultyName());
        faculty.setDesignation(request.getDesignation());
        faculty.setEmail(request.getEmail());
        faculty.setPhoneNumber(request.getPhoneNumber());
        faculty.setSpecialization(request.getSpecialization());

        return toDTO(facultyRespository.save(faculty));
    }

    public FacultyDTO updateFaculty(Long id, FacultyRequestDTO request) {
        Faculty faculty = facultyRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setFacultyName(request.getFacultyName());
        faculty.setDesignation(request.getDesignation());
        faculty.setEmail(request.getEmail());
        faculty.setPhoneNumber(request.getPhoneNumber());
        faculty.setSpecialization(request.getSpecialization());

        return toDTO(facultyRespository.save(faculty));
    }

    public void deleteFaculty(Long id) {
        facultyRespository.deleteById(id);
    }

    private FacultyDTO toDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setFacultyId(faculty.getFacultyId());
        dto.setFacultyName(faculty.getFacultyName());
        dto.setDesignation(faculty.getDesignation());
        dto.setEmail(faculty.getEmail());
        dto.setPhoneNumber(faculty.getPhoneNumber());
        dto.setSpecialization(faculty.getSpecialization());

        if (faculty.getCourses() != null) {
            dto.setCourseNames(
                    faculty.getCourses().stream()
                            .map(course -> course.getCourseName())
                            .collect(Collectors.toList()));
        }

        return dto;
    }

    public Page<Faculty> getPaginatedFaculties(Pageable pageable) {
        return facultyRespository.findAll(pageable);
    }

}
