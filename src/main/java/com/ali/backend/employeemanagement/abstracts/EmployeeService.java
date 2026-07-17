package com.ali.backend.employeemanagement.abstracts;

import com.ali.backend.employeemanagement.dtos.EmployeeCreateDTO;
import com.ali.backend.employeemanagement.dtos.EmployeeUpdateDTO;
import com.ali.backend.employeemanagement.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee findOne(UUID employeeId);

    List<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee updatedOne(EmployeeUpdateDTO employee, UUID employeeId);

    Employee createOne(EmployeeCreateDTO employee);
}
