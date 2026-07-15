package com.ali.backend.employeemanagement.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private UUID id;
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
    private String firstName;

    @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Last name is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Last name is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "Last name is required")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @NotNull(message = "Position is required")
    @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
    private String position;
    private UUID departmentId;
}
