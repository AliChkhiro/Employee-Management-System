package com.ali.backend.employeemanagement.repositories;

import com.ali.backend.employeemanagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
