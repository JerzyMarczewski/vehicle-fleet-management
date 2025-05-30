package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    Vehicle createVehicle(CreateVehicleRequestDTO createVehicleRequestDTO);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(UUID id);
//    Vehicle updateVehicle(UUID id, Vehicle vehicle);
//    void deleteVehicle(UUID id);
}

