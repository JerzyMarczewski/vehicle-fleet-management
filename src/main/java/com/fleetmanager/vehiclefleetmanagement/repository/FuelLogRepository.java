package com.fleetmanager.vehiclefleetmanagement.repository;

import com.fleetmanager.vehiclefleetmanagement.entity.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuelLogRepository extends JpaRepository<FuelLog, UUID> {
}