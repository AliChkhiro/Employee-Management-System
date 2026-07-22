package com.ali.backend.employeemanagement.abstracts;

import com.ali.backend.employeemanagement.dtos.DepartmentCreateDTO;
import com.ali.backend.employeemanagement.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    Department findOne(UUID departmentId);

    List<Department> findAll();

    Department createOne(DepartmentCreateDTO department);

    void deleteOne(UUID departmentId);
}
