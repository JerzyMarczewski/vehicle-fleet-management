package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripSummaryDTO {
    private UUID id;
    private Date startTime;
    private Date endTime;
    private String startLocation;
    private String endLocation;
    private int distanceKm;
}
