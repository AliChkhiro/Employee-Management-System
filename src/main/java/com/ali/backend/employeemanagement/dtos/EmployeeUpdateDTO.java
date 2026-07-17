package com.ali.backend.employeemanagement.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdateDTO(

        @NotNull(message = "First name is required")
        @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
        String firstName,

        @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
        @NotNull(message = "Last name is required")
        String lastName,

        @NotNull(message = "Last name is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
        String phoneNumber,

        @NotNull(message = "Position is required")
        @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 caracters")
        String position
) {
}
