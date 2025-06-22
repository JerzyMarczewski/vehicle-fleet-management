package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.dto.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface TripService {
    Trip createTrip(TripDetailsDTO trip);

    List<Trip> getAllTrips();
    List<TripSummaryDTO> getAllTripSummaryDTOs();
    List<TripDetailsDTO> getAllTripDetailsDTOs();

    Optional<Trip> getTripById(UUID id);
    Optional<TripDetailsDTO> getTripDetailsDTOById(UUID id);

    Trip editTrip(UUID id, TripDetailsDTO changedTrip);

    UUID deleteTripById(UUID id);
}
