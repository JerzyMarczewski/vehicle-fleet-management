package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import com.fleetmanager.vehiclefleetmanagement.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    @Override
    public Optional<Insurance> getInsuranceById(UUID id) {
        return insuranceRepository.findById(id);
    }

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    @Override
    public Insurance updateInsurance(UUID id, Insurance updatedInsurance) {
        return insuranceRepository.findById(id).map(existingInsurance -> {
            existingInsurance.setVehicle(updatedInsurance.getVehicle());
            existingInsurance.setProvider(updatedInsurance.getProvider());
            existingInsurance.setPolicyNumber(updatedInsurance.getPolicyNumber());
            existingInsurance.setValidFrom(updatedInsurance.getValidFrom());
            existingInsurance.setValidTo(updatedInsurance.getValidTo());
            existingInsurance.setCost(updatedInsurance.getCost());
            return insuranceRepository.save(existingInsurance);
        }).orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
    }

    @Override
    public void deleteInsurance(UUID id) {
        insuranceRepository.deleteById(id);
    }
}
