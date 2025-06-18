package com.app.college.repository;

import com.app.college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Page<Student> findAll(Pageable pageable);

}
