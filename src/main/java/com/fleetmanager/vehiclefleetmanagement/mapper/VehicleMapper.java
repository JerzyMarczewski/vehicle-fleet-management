package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.*;
import com.fleetmanager.vehiclefleetmanagement.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
//    VehicleResponseDTO toResponseDTO(Vehicle vehicle);
//    List<VehicleResponseDTO> toResponseDTOList(List<Vehicle> vehicles);
    Vehicle fromCreateDTO(CreateVehicleRequestDTO dto);
}
