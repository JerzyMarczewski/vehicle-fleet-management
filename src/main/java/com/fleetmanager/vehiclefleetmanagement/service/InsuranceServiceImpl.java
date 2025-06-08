package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import com.fleetmanager.vehiclefleetmanagement.mapper.InsuranceMapper;
import com.fleetmanager.vehiclefleetmanagement.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, InsuranceMapper insuranceMapper) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceMapper = insuranceMapper;
    }

    @Override
    public Optional<Insurance> searchByPolicyNumber(String type, String query) {
        return switch (type) {
            case "id" -> {
                try {
                    UUID id = UUID.fromString(query);
                    yield insuranceRepository.findById(id);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Imvalid UUID format");
                }
            }
            default -> throw new IllegalArgumentException("Invalid insurance type");
        };
    }

    @Override
    public Insurance createInsurance(CreateInsuranceRequestDTO createInsuranceRequestDTO) {
        Insurance insurance = insuranceMapper.fromCreateDTO(createInsuranceRequestDTO);
        insuranceRepository.save(insurance);
        return insurance;
    }

    @Override
    public Insurance editInsurance(UUID id, EditInsuranceRequestDTO editInsuranceRequestDTO) {
        Insurance existingInsurance = insuranceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance not found"));

        if (!existingInsurance.getPolicyNumber().equals(editInsuranceRequestDTO.getPolicyNumber())) {
            insuranceRepository.findByPolicyNumberIgnoreCase(editInsuranceRequestDTO.getPolicyNumber()).ifPresent(v -> {
                throw new IllegalArgumentException("Insurance policy number already exists");
            });
        }

        insuranceMapper.updateInsuranceFromDTO(editInsuranceRequestDTO, existingInsurance);
        return insuranceRepository.save(existingInsurance);
    }

    @Override
    public Insurance deleteInsurance(UUID id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Insurance not found"));
        insuranceRepository.delete(insurance);
        return insurance;
    }

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }


    @Override
    public Insurance getInsuranceById(UUID id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
    }
}
