package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.FuelLogDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.FuelLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelLogMapper {
    FuelLog fromDTO(FuelLogDTO dto);

    FuelLogDTO toDTO(FuelLog fuelLog);
}
