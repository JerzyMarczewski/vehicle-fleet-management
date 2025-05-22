package com.fleetmanager.vehiclefleetmanagement.repository;

import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
