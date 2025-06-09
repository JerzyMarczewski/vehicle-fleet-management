package com.fleetmanager.vehiclefleetmanagement.service;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import com.fleetmanager.vehiclefleetmanagement.mapper.TripMapper;
import com.fleetmanager.vehiclefleetmanagement.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class TripServiceImpl implements TripService{
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper){
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
    }

    @Override
    public Trip createTrip(TripDetailsDTO trip) {
        return tripRepository.save(tripMapper.fromDetailsDTO(trip));
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public List<TripSummaryDTO> getAllTripSummaryDTOs() {
        List<Trip> trips = getAllTrips();
        return trips.stream()
                .map(tripMapper::toSummaryDTO)
                .collect(toList());
    }

    @Override
    public List<TripDetailsDTO> getAllTripDetailsDTOs() {
        var trips = getAllTrips();
        return trips.stream()
                .map(tripMapper::toDetailsDTO)
                .collect(toList());
    }

    @Override
    public Optional<Trip> getTripById(UUID id) {
        return tripRepository.findById(id);
    }

    @Override
    public Optional<TripDetailsDTO> getTripDetailsDTOById(UUID id) {
        var trip = getTripById(id);
        return tripMapper.toDetailsDTO(trip);
    }

    @Override
    public UUID deleteTripById(UUID id) {
        if (!tripRepository.existsById(id)){
            throw new RuntimeException("Trip not found with ID: " + id);
        }

        tripRepository.deleteById(id);
        return id;
    }

    @Override
    public Trip editTrip(UUID id, TripDetailsDTO changedTrip) {
         Trip trip = getTripById(id).orElseThrow(() -> new RuntimeException("Trip with ID: " + id + " does not exist"));
         tripMapper.updateTripFromDetailsDTO(changedTrip, trip);

         return tripRepository.save(trip);
    }
}
