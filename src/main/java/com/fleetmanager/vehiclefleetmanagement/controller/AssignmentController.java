package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.AssignmentRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Assignment;
import com.fleetmanager.vehiclefleetmanagement.entity.Driver;
import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.enums.FlashAttribute;
import com.fleetmanager.vehiclefleetmanagement.service.AssignmentService;
import com.fleetmanager.vehiclefleetmanagement.service.DriverService;
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
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final MessageService messageService;

    @GetMapping()
    public String showAssignments(Model model) {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        return "assignment/list";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable UUID id, Model model) {
        Assignment assignment = assignmentService.getAssigmentById(id);

        model.addAttribute("assignment", assignment);
        return "assignment/details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Driver> availableDrivers = driverService.getAllDrivers();
        List<Vehicle> availableVehicles = vehicleService.getAllVehicles();

        model.addAttribute("availableDrivers", availableDrivers);
        model.addAttribute("availableVehicles", availableVehicles);
        model.addAttribute("assignmentRequestDTO", new AssignmentRequestDTO());
        return "assignment/add";
    }

    @PostMapping("/add")
    public String addAssignment(@ModelAttribute("assignmentRequestDTO") AssignmentRequestDTO dto,
                                RedirectAttributes redirectAttributes) {
        assignmentService.addAssignment(dto);
        redirectAttributes.addFlashAttribute(FlashAttribute.SUCCESS_MESSAGE.getAttributeName(),
                messageService.getMessage("assignment.add.success"));
        return "redirect:/assignments";
    }


}
