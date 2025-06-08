package com.fleetmanager.vehiclefleetmanagement.dto;

import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInsuranceRequestDTO {
    private String name;
    private String provider;
    private String policyNumber;
    private UUID vehicleId;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Double cost;
}
