package com.app.college.repository;

import com.app.college.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRespository extends JpaRepository<Faculty, Long>{

    
}
    

