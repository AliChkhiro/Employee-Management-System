package com.ali.backend.employeemanagement.services;

import com.ali.backend.employeemanagement.abstracts.EmployeeService;
import com.ali.backend.employeemanagement.dtos.EmployeeCreateDTO;
import com.ali.backend.employeemanagement.dtos.EmployeeUpdateDTO;
import com.ali.backend.employeemanagement.entities.Department;
import com.ali.backend.employeemanagement.entities.Employee;
import com.ali.backend.employeemanagement.exceptions.CustomResponseException;
import com.ali.backend.employeemanagement.repositories.DepartmentRepository;
import com.ali.backend.employeemanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not found"));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        employee.ifPresent(value -> employeeRepository.deleteById(value.getId()));
    }

    @Override
    public Employee updatedOne(EmployeeUpdateDTO employee, UUID employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not found"));

        existingEmployee.setFirstName(employee.firstName());
        existingEmployee.setLastName(employee.lastName());
        existingEmployee.setPhoneNumber(employee.phoneNumber());
        existingEmployee.setPosition(employee.position());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public Employee createOne(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = new Employee();

        Department department = departmentRepository.findById(employeeCreateDTO.departmentId())
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Department with id " + employeeCreateDTO.departmentId() + " not found"
                ));

        employee.setFirstName(employeeCreateDTO.firstName());
        employee.setLastName(employeeCreateDTO.lastName());
        employee.setPosition(employeeCreateDTO.position());
        employee.setEmail(employeeCreateDTO.email());
        employee.setPhoneNumber(employeeCreateDTO.phoneNumber());
        employee.setHireDate(employeeCreateDTO.hireDate());
        employee.setDepartment(department);

        employeeRepository.save(employee);
        return employee;
    }


}
