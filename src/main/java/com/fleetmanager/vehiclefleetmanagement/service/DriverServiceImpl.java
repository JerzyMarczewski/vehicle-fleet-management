package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.DriverRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Driver;
import com.fleetmanager.vehiclefleetmanagement.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{
    private final DriverRepository driverRepository;

    public  List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public void addDriver(DriverRequestDTO dto) {
        Driver driver = Driver.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .licenseNumber(dto.getLicenseNumber())
                .employmentDate(dto.getEmploymentDate())
                .build();

        driverRepository.save(driver);
    }

    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    public void editDriver(UUID id, DriverRequestDTO dto) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setEmploymentDate(dto.getEmploymentDate());

        driverRepository.save(driver);
    }

    public void deleteDriver(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driverRepository.delete(driver);
    }
}
