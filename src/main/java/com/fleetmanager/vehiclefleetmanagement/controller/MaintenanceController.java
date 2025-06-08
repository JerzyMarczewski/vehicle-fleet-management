package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditMaintenanceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Maintenance;
import com.fleetmanager.vehiclefleetmanagement.entity.MaintenanceType;
import com.fleetmanager.vehiclefleetmanagement.mapper.MaintenanceMapper;
import com.fleetmanager.vehiclefleetmanagement.service.MaintenanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceController(MaintenanceService maintenanceService, MaintenanceMapper maintenanceMapper) {
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }


    @GetMapping("/search-page")
    public String showSearchPage() {
        return "maintenance/search";
    }

    @GetMapping("/search")
    public String searchMaintenance(
            @RequestParam String searchType,
            @RequestParam String searchValue,
            @RequestParam(required = false) String returnUrl,
            @RequestParam(required = false) String selectAction,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Optional<Maintenance> result = maintenanceService.searchBy(searchType, searchValue);

            if (result.isPresent()) {
                if (selectAction != null && !selectAction.isEmpty()) {
                    model.addAttribute("returnUrl", returnUrl);
                    model.addAttribute("selectAction", selectAction);
                    model.addAttribute("insurance", result.get());
                    return "fragments/maintenance-search :: maintenanceSearch";
                }
                return "redirect:/maintenances/" + result.get().getId();
            } else {
                model.addAttribute("noResult", true);
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "fragments/maintenance-search :: maintenanceSearch";
    }

    @GetMapping
    public String showMaintenance(Model model) {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        model.addAttribute("maintenances", maintenances);
        return "maintenance/list";
    }

    @GetMapping("/{id}")
    public String getMaintenanceById(@PathVariable UUID id, Model model) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        model.addAttribute("maintenance", maintenance);
        return "maintenance/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("createMaintenanceRequestDTO", new CreateMaintenanceRequestDTO());
        model.addAttribute("maintenanceTypes", MaintenanceType.values());
        return "maintenance/create";
    }

    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute("createMaintenanceRequestDTO") CreateMaintenanceRequestDTO dto) {
        maintenanceService.createMaintenance(dto);
        return "redirect:/maintenances";

    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        model.addAttribute("editMaintenanceRequestDTO", maintenanceMapper.toEditDTO(maintenance));
        model.addAttribute("maintenanceId", id);
        model.addAttribute("maintenanceTypes", MaintenanceType.values());
        return "maintenance/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMaintenance(
            @PathVariable UUID id,
            @ModelAttribute @DateTimeFormat(pattern = "yyyy-MM-dd") EditMaintenanceRequestDTO dto
    ) {
        maintenanceService.editMaintenance(id, dto);
        return "redirect:/maintenances/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteMaintenance(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        maintenanceService.deleteMaintenance(id);
        redirectAttributes.addFlashAttribute("message", "Insurance has been deleted");
        return "redirect:/maintenances";

    }
}
