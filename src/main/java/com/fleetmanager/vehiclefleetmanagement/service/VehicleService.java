package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.AddVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.enums.VehicleSearchType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleService {
    Optional<Vehicle> searchByType(VehicleSearchType type, String query);
    Vehicle addVehicle(AddVehicleRequestDTO addVehicleRequestDTO);
    Vehicle editVehicle(UUID id, EditVehicleRequestDTO editVehicleRequestDTO);
    Vehicle deleteVehicle(UUID id);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(UUID id);
}

