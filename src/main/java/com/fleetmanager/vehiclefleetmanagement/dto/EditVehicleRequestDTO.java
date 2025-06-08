package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: consider merging this and create dto together

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditVehicleRequestDTO {
    private String registrationNumber;
    private String vin;
    private String brand;
    private String model;
    private Integer productionYear;
    private Integer mileage;
}

