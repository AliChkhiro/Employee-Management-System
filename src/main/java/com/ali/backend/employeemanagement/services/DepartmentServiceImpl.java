package com.ali.backend.employeemanagement.services;

import com.ali.backend.employeemanagement.abstracts.DepartmentService;
import com.ali.backend.employeemanagement.dtos.DepartmentCreateDTO;
import com.ali.backend.employeemanagement.entities.Department;
import com.ali.backend.employeemanagement.exceptions.CustomResponseException;
import com.ali.backend.employeemanagement.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findOne(UUID departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Department with id " + departmentId + " not found"
                ));
        return department;
    }

    @Override
    public List<Department> findAll() {
//        List<Department> departments = departmentRepository.findAll();
//        return departments;
        return departmentRepository.findAll();
    }

    @Override
    public Department createOne(DepartmentCreateDTO departmentCreateDTO) {
        Department department = new Department();
        department.setName(departmentCreateDTO.name());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public void deleteOne(UUID departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        department.ifPresent(department1 -> departmentRepository.deleteById(department1.getId()));
    }
}
