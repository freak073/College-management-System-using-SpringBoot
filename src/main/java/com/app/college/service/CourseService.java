package com.app.college.service;

import com.app.college.dto.CourseDTO;
import com.app.college.dto.CourseRequestDTO;
import com.app.college.model.Course;
import com.app.college.model.Enrollment;
import com.app.college.model.Faculty;
import com.app.college.repository.CourseRepository;
import com.app.college.repository.FacultyRespository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRespository facultyRespository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public CourseDTO addCourse(CourseRequestDTO request) {
        Faculty faculty = facultyRespository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setCredits(request.getCredits());
        course.setDurations(request.getDurations());
        course.setFaculty(faculty);

        return toDTO(courseRepository.save(course));
    }

    public CourseDTO updateCourse(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Faculty faculty = facultyRespository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        course.setCourseName(request.getCourseName());
        course.setCredits(request.getCredits());
        course.setDurations(request.getDurations());
        course.setFaculty(faculty);

        return toDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());
        dto.setDurations(course.getDurations());

        if (course.getFaculty() != null) {
            dto.setFacultyId(course.getFaculty().getFacultyId());
            dto.setFacultyName(course.getFaculty().getFacultyName());
        }

        return dto;
    }

        public Page<Course> getPaginatedcourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

}
