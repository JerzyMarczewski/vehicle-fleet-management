package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.mapper.VehicleMapper;
import com.fleetmanager.vehiclefleetmanagement.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Optional<Vehicle> searchByType(String type, String query) {
        return switch (type) {
            case "id" -> {
                try {
                    UUID id = UUID.fromString(query);
                    yield vehicleRepository.findById(id);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid UUID format");
                }
            }
            case "registration" -> vehicleRepository.findByRegistrationNumberIgnoreCase(query);
            case "vin" -> vehicleRepository.findByVinIgnoreCase(query);
            default -> throw new IllegalArgumentException("Invalid search type");
        };
    }

    @Override
    public Vehicle createVehicle(CreateVehicleRequestDTO createVehicleRequestDTO) {
        Vehicle vehicle = vehicleMapper.fromCreateDTO(createVehicleRequestDTO);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
    }
}
