package com.fleetmanager.vehiclefleetmanagement.DTO.trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDetailsDTO {
    private UUID id;
    private UUID driverId;
    private UUID vehicleId;
    String purpose;
    private Date startTime;
    private Date endTime;
    private String startLocation;
    private String endLocation;
    private int distanceKm;
}
