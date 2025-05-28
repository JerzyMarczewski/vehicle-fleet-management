package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceService {
    Insurance saveInsurance(Insurance insurance);
    Optional<Insurance> getInsuranceById(UUID id);
    List<Insurance> getAllInsurances();
    Insurance updateInsurance(UUID id, Insurance insurance);
    void deleteInsurance(UUID id);
}


