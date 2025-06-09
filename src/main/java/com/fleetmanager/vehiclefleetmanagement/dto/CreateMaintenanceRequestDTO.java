package com.fleetmanager.vehiclefleetmanagement.dto;

import com.fleetmanager.vehiclefleetmanagement.entity.MaintenanceType;
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
public class CreateMaintenanceRequestDTO {
    private UUID vehicleId;
    private String description;
    private Double cost;
    private MaintenanceType type;
    private LocalDate date;
}
