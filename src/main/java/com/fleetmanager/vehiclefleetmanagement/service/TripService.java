package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {

    List<Trip> getAllTrips();
    Trip createTrip(Trip trip);
}
