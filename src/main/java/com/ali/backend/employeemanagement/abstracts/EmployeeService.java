package com.ali.backend.employeemanagement.abstracts;

import com.ali.backend.employeemanagement.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {

    Employee findOne(UUID employeeId);

    ArrayList<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee updatedOne(Employee employee, UUID employeeId);

    Employee createOne(Employee employee);
}
