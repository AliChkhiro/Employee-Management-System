package com.ali.backend.employeemanagement.controllers;

import com.ali.backend.employeemanagement.entities.Employee;
import com.ali.backend.employeemanagement.exceptions.CustomResponseException;
import com.ali.backend.employeemanagement.exceptions.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not found"));

        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(
            @PathVariable UUID employeeId,
            @RequestBody @Valid Employee employee) {
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

        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        employee.ifPresent(value -> employees.remove(value));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
