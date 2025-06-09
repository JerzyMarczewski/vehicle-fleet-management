package com.fleetmanager.vehiclefleetmanagement.dto;

import com.fleetmanager.vehiclefleetmanagement.entity.MaintenanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditMaintenanceRequestDTO {
    private String description;
    private Double cost;
    private MaintenanceType type;
    private UUID vehicleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
