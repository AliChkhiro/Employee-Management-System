package com.ali.backend.employeemanagement.abstracts;

import com.ali.backend.employeemanagement.dtos.LeaveRequestCreateDTO;
import com.ali.backend.employeemanagement.entities.LeaveRequest;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {

    LeaveRequest createOneRequest(
            LeaveRequestCreateDTO leaveRequestCreateDTO,
            UUID employeeId);

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}
