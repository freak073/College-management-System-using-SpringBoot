package com.app.college.repository;
import com.app.college.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // Additional query methods can be defined here if needed
    
    
}
