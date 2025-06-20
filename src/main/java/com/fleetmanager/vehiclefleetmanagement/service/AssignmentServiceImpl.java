package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.AssignmentRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Assignment;
import com.fleetmanager.vehiclefleetmanagement.entity.Driver;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.repository.AssignmentRepository;
import com.fleetmanager.vehiclefleetmanagement.repository.DriverRepository;
import com.fleetmanager.vehiclefleetmanagement.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService{

    public final AssignmentRepository assignmentRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssigmentById(UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    public void addAssignment(AssignmentRequestDTO dto) {
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Assignment assignment =  Assignment.builder()
                .driver(driver)
                .vehicle(vehicle)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        assignmentRepository.save(assignment);
    }
}
