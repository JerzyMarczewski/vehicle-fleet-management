package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {
    Insurance fromCreateDTO(CreateInsuranceRequestDTO dto);
    Insurance fromEditDTO(EditInsuranceRequestDTO dto);

    EditInsuranceRequestDTO toEditDTO(Insurance insurance);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInsuranceFromDTO(EditInsuranceRequestDTO dto, @MappingTarget Insurance insurance);
}
