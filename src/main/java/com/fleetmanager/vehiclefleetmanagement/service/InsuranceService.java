package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceService {
    Optional<Insurance> searchByPolicyNumber(String type, String query);
    Insurance createInsurance(CreateInsuranceRequestDTO createInsuranceRequestDTO);
    Insurance editInsurance(UUID id, EditInsuranceRequestDTO editInsuranceRequestDTO);

    Insurance deleteInsurance(UUID id);

    List<Insurance> getAllInsurances();
    Insurance getInsuranceById(UUID id);
}


