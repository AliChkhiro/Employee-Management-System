package com.ali.backend.employeemanagement.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeaveRequestCreateDTO(

        @NotNull(message = "Reason is required")
        @Size(min = 2, max = 100, message = "min is 2 characters and max is 100 characters")
        String reason,


        @NotNull(message = "Start date is required")
        @PastOrPresent(message = "Start date should be Now or in the Future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @PastOrPresent(message = "End date should be Now or in the Future")
        LocalDate endDate


) {
}
