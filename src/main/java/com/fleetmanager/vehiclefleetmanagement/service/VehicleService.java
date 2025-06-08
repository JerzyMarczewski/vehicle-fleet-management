package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleService {
    Optional<Vehicle> searchByType(String type, String query);
    Vehicle createVehicle(CreateVehicleRequestDTO createVehicleRequestDTO);
    Vehicle editVehicle(UUID id, EditVehicleRequestDTO editVehicleRequestDTO);
    Vehicle deleteVehicle(UUID id);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(UUID id);
}