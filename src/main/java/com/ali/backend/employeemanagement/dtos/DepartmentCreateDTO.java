package com.ali.backend.employeemanagement.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreateDTO(
        @NotNull(message = "name ir required")
        @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 characters")
        String name
) {
}
