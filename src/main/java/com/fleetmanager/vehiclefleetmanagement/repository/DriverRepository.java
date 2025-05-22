package com.fleetmanager.vehiclefleetmanagement.repository;


import com.fleetmanager.vehiclefleetmanagement.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}