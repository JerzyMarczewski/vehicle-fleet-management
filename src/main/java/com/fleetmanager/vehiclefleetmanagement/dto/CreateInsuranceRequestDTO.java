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

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInsuranceRequestDTO {
    private String name;
    private String provider;
    private String policyNumber;
    private Date validFrom;
    private Date validTo;
    private Double cost;
}
