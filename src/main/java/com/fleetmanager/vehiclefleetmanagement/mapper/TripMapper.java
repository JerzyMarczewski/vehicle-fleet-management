package com.fleetmanager.vehiclefleetmanagement.mapper;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripSummaryDTO toSummaryDTO(Trip trip);
    Trip fromSummaryDTO(TripSummaryDTO dto);

    TripDetailsDTO toDetailsDTO(Trip trip);

    Trip fromDetailsDTO(TripDetailsDTO dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTripFromDetailsDTO(TripDetailsDTO dto, @MappingTarget Trip trip);
}
