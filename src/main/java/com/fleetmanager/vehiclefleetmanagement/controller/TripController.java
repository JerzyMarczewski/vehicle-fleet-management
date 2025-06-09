package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.mapper.TripMapper;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
import com.fleetmanager.vehiclefleetmanagement.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;
    private final TripMapper tripMapper;


    public TripController(TripService tripService, TripMapper tripMapper){
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @GetMapping
    public String showAllTrips(Model model){
        model.addAttribute("trips", tripService.getAllTripSummaryDTOs());

       return "trip/index";
    }

    @GetMapping("/{id}")
    public String showTripDetails(@PathVariable("id") UUID id, Model model){
        TripDetailsDTO trip = tripService.getTripDetailsDTOById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        model.addAttribute("trip", trip);

        return "trip/details";
    }

    @PutMapping("/{id}")
    public String updateTrip(@RequestBody TripDetailsDTO trip, @PathVariable UUID id){
        tripService.editTrip(id, trip);
        return "redirect:trip/details?id=" + id;
    }

    @DeleteMapping
    public String deleteTrip(@RequestBody UUID id){
        tripService.deleteTripById(id);
        return "redirect:/trip/index";
    }

    @GetMapping("/new")
    public String showNewTripForm(){
        return "trip/create";
    }

    @PostMapping("/new")
    public String createNewTrip(@RequestBody TripDetailsDTO trip){
        Trip newTrip = tripService.createTrip(trip);
        return "redirect:/trip/details?id="+newTrip.getId();
    }
}
