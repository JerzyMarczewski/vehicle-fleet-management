package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.AssignmentRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Assignment;

import java.util.List;
import java.util.UUID;

public interface AssignmentService {
    List<Assignment> getAllAssignments();
    Assignment getAssigmentById(UUID id);
    void addAssignment(AssignmentRequestDTO dto);
}
