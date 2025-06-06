package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.*;
import com.fleetmanager.vehiclefleetmanagement.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle fromAddDTO(AddVehicleRequestDTO dto);
    Vehicle fromEditDTO(EditVehicleRequestDTO dto);

    EditVehicleRequestDTO toEditDTO(Vehicle vehicle);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVehicleFromDTO(EditVehicleRequestDTO dto, @MappingTarget Vehicle vehicle);

}
