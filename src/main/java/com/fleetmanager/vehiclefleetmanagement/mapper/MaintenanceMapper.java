package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
   Maintenance fromCreateDTO(CreateMaintenanceRequestDTO dto);
   Maintenance fromEditDTO(EditMaintenanceRequestDTO dto);

   EditMaintenanceRequestDTO toEditDTO(Maintenance maintenance);
   @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMaintenanceFromDTO(EditMaintenanceRequestDTO dto, @MappingTarget Maintenance maintenance);
}
