package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.CreateMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaintenanceService {
    Optional<Maintenance> searchBy(String type, String query);
    Maintenance createMaintenance(CreateMaintenanceRequestDTO createMaintenanceRequestDTO);
    Maintenance editMaintenance(UUID id, EditMaintenanceRequestDTO editMaintenanceRequestDTO);

    Maintenance deleteMaintenance(UUID id);

    List<Maintenance> getAllMaintenances();
    Maintenance getMaintenanceById(UUID id);
}

