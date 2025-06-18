
package com.app.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.college.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
