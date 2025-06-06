package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.enums.VehicleSearchContext;
import com.fleetmanager.vehiclefleetmanagement.dto.AddVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditVehicleRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.enums.VehicleSearchType;
import com.fleetmanager.vehiclefleetmanagement.handler.VehicleSearchHandler;
import com.fleetmanager.vehiclefleetmanagement.mapper.VehicleMapper;
import com.fleetmanager.vehiclefleetmanagement.service.MessageService;
import com.fleetmanager.vehiclefleetmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleSearchHandler vehicleSearchHandler;
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;
    private final MessageService messageService;

    @GetMapping("/search-page")
    public String showSearchPage() {
        return "vehicle/search";
    }


    @GetMapping("/search")
    public String searchVehicle(
            @RequestParam VehicleSearchType searchType,
            @RequestParam String searchValue,
            RedirectAttributes redirectAttributes) {
        return vehicleSearchHandler.handleVehicleSearch(searchType, searchValue, VehicleSearchContext.DETAILS, redirectAttributes);
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

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("addVehicleRequestDTO", new AddVehicleRequestDTO());
        return "vehicle/add";
    }

    @PostMapping("/add")
    public String addVehicle(@ModelAttribute("addVehicleRequestDTO") AddVehicleRequestDTO dto) {
        vehicleService.addVehicle(dto);
        return "redirect:/vehicles";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("editVehicleRequestDTO", vehicleMapper.toEditDTO(vehicle));
        model.addAttribute("vehicleId", id);
        return "vehicle/edit";
    }

    @PostMapping("/{id}/edit")
    public String editVehicle(@PathVariable UUID id,
                              @ModelAttribute EditVehicleRequestDTO dto) {
        vehicleService.editVehicle(id, dto);
        return "redirect:/vehicles/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(id);
        redirectAttributes.addFlashAttribute("successMessage",
                messageService.getMessage("vehicle.delete.success"));
        return "redirect:/vehicles";
    }
}
