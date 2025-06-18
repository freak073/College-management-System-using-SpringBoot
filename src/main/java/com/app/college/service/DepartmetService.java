package com.app.college.service;

import com.app.college.model.Department;
import com.app.college.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class DepartmetService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAlldepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        updatedDepartment.setDepartmentId(id);
        return departmentRepository.save(updatedDepartment);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Page<Department> getDepartmentsPaginated(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

}
