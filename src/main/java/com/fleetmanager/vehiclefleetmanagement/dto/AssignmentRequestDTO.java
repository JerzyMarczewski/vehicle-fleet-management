package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentRequestDTO {
    private UUID driverId;
    private UUID vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
}