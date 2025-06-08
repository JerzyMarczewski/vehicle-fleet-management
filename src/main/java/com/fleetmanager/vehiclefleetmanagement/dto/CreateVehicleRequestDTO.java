package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequestDTO {
    private String registrationNumber;
    private String vin;
    private String brand;
    private String model;
    private Integer productionYear;
    private Integer mileage;
}
