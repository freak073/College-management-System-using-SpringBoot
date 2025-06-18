// package com.app.college.service;

// import lombok.RequiredArgsConstructor;
// import com.app.college.dto.StudentDTO;
// import com.app.college.dto.StudentRequestDTO;
// import com.app.college.model.Department;
// import com.app.college.repository.DepartmentRepository;

// import com.app.college.model.Student;
// import com.app.college.repository.StudentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.stream.Collectors;

// import java.util.List;
// @Service
// @RequiredArgsConstructor
// public class StudentService {

//     @Autowired
//     private final StudentRepository studentRepository;
//     private final DepartmentRepository departmentRepository;

//     public StudentDTO addStudent(StudentRequestDTO dto) {
//         Student student = new Student();
//         mapToEntity(dto, student);
//         return toDTO(studentRepository.save(student));
//     }

//     public StudentDTO updateStudent(Long id, StudentRequestDTO dto) {
//         Student student = studentRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Student not found"));
//         mapToEntity(dto, student);
//         return toDTO(studentRepository.save(student));
//     }

//     public StudentDTO getStudentById(Long id) {
//         return studentRepository.findById(id)
//                 .map(this::toDTO)
//                 .orElseThrow(() -> new RuntimeException("Student not found"));
//     }

//     public List<StudentDTO> getAllStudents() {
//         return studentRepository.findAll().stream()
//                 .map(this::toDTO)
//                 .collect(Collectors.toList());
//     }

//     public void deleteStudent(Long id) {
//         studentRepository.deleteById(id);
//     }

//     private void mapToEntity(StudentRequestDTO dto, Student student) {
//         student.setStudentName(dto.getStudentName());
//         student.setEmail(dto.getEmail());
//         student.setCourse(dto.getCourse());
//         student.setBranch(dto.getBranch());
//         student.setAddress(dto.getAddress());
//         student.setPhoneNumber(dto.getPhoneNumber());
//         student.setDateOfBirth(dto.getDateOfBirth());

//         if (dto.getDepartmentId() != null) {
//             Department dept = departmentRepository.findById(dto.getDepartmentId())
//                     .orElseThrow(() -> new RuntimeException("Department not found"));
//             student.setDepartment(dept);
//         }
//     }

//     private StudentDTO toDTO(Student student) {
//         StudentDTO dto = new StudentDTO();
//         dto.setStudentId(student.getStudentId());
//         dto.setStudentName(student.getStudentName());
//         dto.setEmail(student.getEmail());
//         dto.setCourse(student.getCourse());
//         dto.setBranch(student.getBranch());
//         dto.setAddress(student.getAddress());
//         dto.setPhoneNumber(student.getPhoneNumber());
//         dto.setDateOfBirth(student.getDateOfBirth());
//         dto.setDepartmentName(student.getDepartment() != null ? student.getDepartment().getDepartmentName() : null);
//         return dto;
//     }
// }
package com.app.college.service;

import com.app.college.dto.StudentDTO;
import com.app.college.dto.StudentRequestDTO;
import com.app.college.model.Department;
import com.app.college.model.Student;
import com.app.college.repository.DepartmentRepository;
import com.app.college.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // ✅ Convert Entity to DTO
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getStudentId(),
                student.getStudentName(),
                student.getEmail(),
                student.getCourse(),
                student.getBranch(),
                student.getAddress(),
                student.getPhoneNumber(),
                student.getDateOfBirth(),
                student.getDepartment() != null ? student.getDepartment().getDepartmentName() : null);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<StudentDTO> getAllStudentsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(this::convertToDTO);
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return convertToDTO(student);
    }

    public StudentDTO addStudent(StudentRequestDTO dto) {
        Student student = new Student();
        student.setStudentName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setBranch(dto.getBranch());
        student.setAddress(dto.getAddress());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());

        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
        student.setDepartment(department);

        return convertToDTO(studentRepository.save(student));
    }

    public StudentDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setStudentName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setBranch(dto.getBranch());
        student.setAddress(dto.getAddress());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDateOfBirth(dto.getDateOfBirth());

        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow();
        student.setDepartment(department);

        return convertToDTO(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Page<StudentDTO> getAllStudentsPaginated(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(this::convertToDTO);
    }

}
