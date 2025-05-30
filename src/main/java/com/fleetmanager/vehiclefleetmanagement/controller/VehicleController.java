package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public String getAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @GetMapping("/{id}")
    public String getVehicleById(@PathVariable UUID id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("createVehicleRequestDTO", new CreateVehicleRequestDTO());
        return "vehicle/create";
    }

    @PostMapping("/create")
    public String createVehicle(@ModelAttribute("createVehicleRequestDTO") CreateVehicleRequestDTO dto) {
        vehicleService.createVehicle(dto);
        return "redirect:/vehicle/list";
    }
}
