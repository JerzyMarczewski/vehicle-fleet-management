package com.fleetmanager.vehiclefleetmanagement.repository;

import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByRegistrationNumberIgnoreCase(String registrationNumber);
    Optional<Vehicle> findByVinIgnoreCase(String vin);

}