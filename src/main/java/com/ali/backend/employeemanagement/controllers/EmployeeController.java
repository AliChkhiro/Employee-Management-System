package com.ali.backend.employeemanagement.controllers;

import com.ali.backend.employeemanagement.abstracts.EmployeeService;
import com.ali.backend.employeemanagement.dtos.EmployeeCreateDTO;
import com.ali.backend.employeemanagement.dtos.EmployeeUpdateDTO;
import com.ali.backend.employeemanagement.entities.Employee;
import com.ali.backend.employeemanagement.exceptions.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {
        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(
            @PathVariable UUID employeeId,
            @RequestBody @Valid EmployeeUpdateDTO employee) {
        Employee updatedEmployee = employeeService.updatedOne(employee, employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(updatedEmployee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreateDTO employee) {
        Employee newEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(newEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
