package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.mapper.MaintenanceMapper;
import com.fleetmanager.vehiclefleetmanagement.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final VehicleService vehicleService;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, VehicleService vehicleService) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.vehicleService = vehicleService;
    }

    @Override
    public Optional<Maintenance> searchBy(String type, String query) {
        return switch (type) {
            case "id" -> {
                try {
                    UUID id = UUID.fromString(query);
                    yield maintenanceRepository.findById(id);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Imvalid UUID format");
                }
            }
            default -> throw new IllegalArgumentException("Invalid maintenance type");
        };
    }

    @Override
    public Maintenance createMaintenance(CreateMaintenanceRequestDTO createMaintenanceRequestDTO) {
        Vehicle vehicle = vehicleService.getVehicleById(createMaintenanceRequestDTO.getVehicleId());

        Maintenance maintenance = maintenanceMapper.fromCreateDTO(createMaintenanceRequestDTO);

        maintenance.setVehicle(vehicle);

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance editMaintenance(UUID id, EditMaintenanceRequestDTO editMaintenanceRequestDTO) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance not found"));

        maintenanceMapper.updateMaintenanceFromDTO(editMaintenanceRequestDTO, existingMaintenance);
        return maintenanceRepository.save(existingMaintenance);
    }

    @Override
    public Maintenance deleteMaintenance(UUID id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance not found"));
        maintenanceRepository.delete(maintenance);
        return maintenance;
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }


    @Override
    public Maintenance getMaintenanceById(UUID id) {
        return maintenanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
    }
}
