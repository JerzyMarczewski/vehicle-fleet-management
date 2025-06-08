package com.fleetmanager.vehiclefleetmanagement.Mapper;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TripMapper {
    public TripSummaryDTO toSummaryDTO(Trip trip);
    public Trip fromSummaryDTO(TripSummaryDTO dto);

    public TripDetailsDTO toDetailsDTO(Trip trip);
    public Trip fromDetailsDTO(TripDetailsDTO dto);
}
