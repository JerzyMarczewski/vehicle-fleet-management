package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelLogDTO {
    private UUID id;
    private Date date;
    private double cost;
    private int mileage;
}
