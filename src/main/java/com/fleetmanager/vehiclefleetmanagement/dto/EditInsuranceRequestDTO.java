package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditInsuranceRequestDTO {
    private String name;
    private String provider;
    private String policyNumber;
    private UUID vehicleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTo;
    private Double cost;
}
