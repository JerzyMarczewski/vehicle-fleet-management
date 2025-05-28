package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaintenanceService {
    Maintenance saveMaintenance(Maintenance maintenance);
    Optional<Maintenance> getMaintenanceById(UUID id);
    List<Maintenance> getAllMaintenances();
    Maintenance updateMaintenance(UUID id, Maintenance maintenance);
    void deleteMaintenance(UUID id);
}

