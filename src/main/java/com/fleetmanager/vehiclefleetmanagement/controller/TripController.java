package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.Mapper.TripMapper;
import com.fleetmanager.vehiclefleetmanagement.service.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.util.stream.Collectors.toList;

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
        model.addAttribute("trips",
            tripService.getAllTrips()
                .stream()
                .map(tripMapper::toSummaryDTO)
                .collect(toList()));
       return "trip/index";
    }

    @GetMapping("/{id}")
    public TripSummaryDTO showTripDetails(@RequestParam("id") UUID id){
        return null;
    }

    @PutMapping("/{id}")
    public TripSummaryDTO updateTrip(@RequestBody TripSummaryDTO trip, @PathVariable String id){
        return null;
    }

    @DeleteMapping
    public TripSummaryDTO deleteTrip(@RequestBody UUID id){
        return null;
    }

    @GetMapping("/new")
    public TripSummaryDTO showNewTripForm(){
        return null;
    }

    @PostMapping("/new")
    public TripSummaryDTO createNewTrip(@RequestBody TripSummaryDTO trip){
        return null;
    }
}
