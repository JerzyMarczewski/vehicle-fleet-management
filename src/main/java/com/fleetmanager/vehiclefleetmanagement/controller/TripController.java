package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripDetailsDTO;
import com.fleetmanager.vehiclefleetmanagement.DTO.trip.TripSummaryDTO;
import com.fleetmanager.vehiclefleetmanagement.Mapper.TripMapper;
import com.fleetmanager.vehiclefleetmanagement.entity.Trip;
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
    public String showTripDetails(@RequestParam("id") UUID id){
        return "trip/details";
    }

    @PutMapping("/{id}")
    public String updateTrip(@RequestBody TripSummaryDTO trip, @PathVariable String id){
        return "redirect:trip/details?id=" + id;
    }

    @DeleteMapping
    public String deleteTrip(@RequestBody UUID id){
        return "redirect:/trip/index";
    }

    @GetMapping("/new")
    public String showNewTripForm(){
        return "trip/create";
    }

    @PostMapping("/new")
    public String createNewTrip(@RequestBody TripDetailsDTO trip){
        Trip newTrip = tripMapper.fromDetailsDTO(trip);
        
        newTrip = tripService.createTrip(newTrip);
        return "redirect:/trip/details?id="+newTrip.getId();
    }
}
