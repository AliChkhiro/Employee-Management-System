package com.ali.backend.employeemanagement.services;

import com.ali.backend.employeemanagement.abstracts.EmployeeService;
import com.ali.backend.employeemanagement.entities.Employee;
import com.ali.backend.employeemanagement.exceptions.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not found"));
        return employee;
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        employee.ifPresent(value -> employees.remove(value));
    }

    @Override
    public Employee updatedOne(Employee employee, UUID employeeId) {
        Employee existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not found"));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setDepartmentId(existingEmployee.getDepartmentId());

        return existingEmployee;
    }

    @Override
    public Employee createOne(Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);

        return employee;
    }


}
