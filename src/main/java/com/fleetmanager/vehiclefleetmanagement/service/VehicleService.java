package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

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

    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id);
    }

    public Vehicle update(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = repository.findById(id).orElseThrow();
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setRegistrationNumber(vehicleDetails.getRegistrationNumber());
        vehicle.setVin(vehicleDetails.getVin());
        vehicle.setProductionYear(vehicleDetails.getProductionYear());
        return repository.save(vehicle);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}