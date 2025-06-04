package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditVehicleRequestDTO;
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
    public Vehicle editVehicle(UUID id, EditVehicleRequestDTO editVehicleRequestDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

        if (!existingVehicle.getRegistrationNumber().equals(editVehicleRequestDTO.getRegistrationNumber())) {
            vehicleRepository.findByRegistrationNumberIgnoreCase(editVehicleRequestDTO.getRegistrationNumber())
                    .ifPresent(v -> {
                        throw new RuntimeException("Registration number already in use");
                    });
        }

        if (!existingVehicle.getVin().equals(editVehicleRequestDTO.getVin())) {
            vehicleRepository.findByVinIgnoreCase(editVehicleRequestDTO.getVin())
                    .ifPresent(v -> {
                        throw new RuntimeException("VIN already in use");
                    });
        }

        vehicleMapper.updateVehicleFromDTO(editVehicleRequestDTO, existingVehicle);
        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public Vehicle deleteVehicle(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
        vehicleRepository.delete(vehicle);
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
