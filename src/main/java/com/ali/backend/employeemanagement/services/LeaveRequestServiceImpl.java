package com.ali.backend.employeemanagement.services;

import com.ali.backend.employeemanagement.abstracts.LeaveRequestService;
import com.ali.backend.employeemanagement.dtos.LeaveRequestCreateDTO;
import com.ali.backend.employeemanagement.entities.Employee;
import com.ali.backend.employeemanagement.entities.LeaveRequest;
import com.ali.backend.employeemanagement.exceptions.CustomResponseException;
import com.ali.backend.employeemanagement.repositories.EmployeeRepository;
import com.ali.backend.employeemanagement.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequest createOneRequest(
            LeaveRequestCreateDTO leaveRequestCreateDTO,
            UUID employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.resourceNotFound(
                        "Employee with id " + employeeId + " not Found"
                ));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(leaveRequestCreateDTO.reason());
        leaveRequest.setStartDate(leaveRequestCreateDTO.startDate());
        leaveRequest.setEndDate(leaveRequestCreateDTO.endDate());
        leaveRequest.setEmployee(employee);
        leaveRequestRepository.save(leaveRequest);
        return leaveRequest;
    }

    @Override
    public List<LeaveRequest> findAllByEmployeeId(UUID employeeId) {
        return leaveRequestRepository.findAllByEmployeeId(employeeId);

    }
}
