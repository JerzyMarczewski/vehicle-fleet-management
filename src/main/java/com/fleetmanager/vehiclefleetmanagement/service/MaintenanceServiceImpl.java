package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;
import com.fleetmanager.vehiclefleetmanagement.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance saveMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Optional<Maintenance> getMaintenanceById(UUID id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance updateMaintenance(UUID id, Maintenance updatedMaintenance) {
        return maintenanceRepository.findById(id).map(existing -> {
            existing.setVehicle(updatedMaintenance.getVehicle());
            existing.setDate(updatedMaintenance.getDate());
            existing.setDescription(updatedMaintenance.getDescription());
            existing.setCost(updatedMaintenance.getCost());
            existing.setType(updatedMaintenance.getType());
            return maintenanceRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
    }

    @Override
    public void deleteMaintenance(UUID id) {
        maintenanceRepository.deleteById(id);
    }
}
