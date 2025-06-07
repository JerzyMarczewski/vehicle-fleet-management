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

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Optional<Vehicle> findById(UUID id) {
        return repository.findById(id);
    }

    public Vehicle update(UUID id, Vehicle vehicleDetails) {
        Vehicle vehicle = repository.findById(id).orElseThrow();
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setRegistrationNumber(vehicleDetails.getRegistrationNumber());
        vehicle.setVin(vehicleDetails.getVin());
        vehicle.setProductionYear(vehicleDetails.getProductionYear());
        return repository.save(vehicle);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
