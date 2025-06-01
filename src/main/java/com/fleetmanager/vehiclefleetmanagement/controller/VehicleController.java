package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/search")
    public String searchVehicle(
            @RequestParam String searchType,
            @RequestParam String searchValue,
            @RequestParam(required = false) String returnUrl,
            @RequestParam(required = false) String selectAction,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            Optional<Vehicle> result = vehicleService.searchByType(searchType, searchValue);

            if (result.isPresent()) {
                if (selectAction != null && !selectAction.isEmpty()) {
                    model.addAttribute("vehicle", result.get());
                    model.addAttribute("selectAction", selectAction);
                    model.addAttribute("returnUrl", returnUrl);
                    return "fragments/vehicle-search :: vehicleSearch";
                }
                return "redirect:/vehicles/" + result.get().getId();
            } else {
                model.addAttribute("noResults", true);
            }

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "fragments/vehicle-search :: vehicleSearch";
    }


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
        return "redirect:/vehicles";
    }
}
