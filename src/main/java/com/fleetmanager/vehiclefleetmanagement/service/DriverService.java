package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.DriverRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Driver;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    List<Driver> getAllDrivers();
    void addDriver(DriverRequestDTO dto);
    Driver getDriverById(UUID id);
    void editDriver(UUID id, DriverRequestDTO dto);
    void deleteDriver(UUID id);
}
