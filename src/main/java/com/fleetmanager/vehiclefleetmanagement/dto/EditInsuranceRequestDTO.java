package com.fleetmanager.vehiclefleetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditInsuranceRequestDTO {
    private String name;
    private String provider;
    private String policyNumber;
    private Date validFrom;
    private Date validTo;
    private Double cost;
}
