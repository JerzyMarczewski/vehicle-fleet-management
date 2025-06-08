package com.fleetmanager.vehiclefleetmanagement.Mapper;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import org.springframework.stereotype.Component;

public class TripMapper {
    public TripSummaryDTO toSummaryDTO(Trip trip){
        return new TripSummaryDTO(
                trip.getId(),
                trip.getStartTime(),
                trip.getEndTime(),
                trip.getStartLocation(),
                trip.getEndLocation(),
                trip.getDistanceKm()
        );
    }
}
