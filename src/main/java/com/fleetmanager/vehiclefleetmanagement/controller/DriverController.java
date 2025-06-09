package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.DriverRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Driver;
import com.fleetmanager.vehiclefleetmanagement.enums.FlashAttribute;
import com.fleetmanager.vehiclefleetmanagement.service.DriverService;
import com.fleetmanager.vehiclefleetmanagement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private static final String REDIRECT_TO_DRIVERS_LIST = "redirect:/drivers";

    private final DriverService driverService;
    private final MessageService messageService;

    @GetMapping
    public String showDrivers(Model model) {
        List<Driver> drivers = driverService.getAllDrivers();
        model.addAttribute("drivers", drivers);
        return "driver/list";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable UUID id,Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driver", driver);
        return "driver/details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("driverRequestDTO", new DriverRequestDTO());
        return "driver/add";
    }

    @PostMapping("/add")
    public String addDriver(@ModelAttribute("driverRequestDTO") DriverRequestDTO dto,
                            RedirectAttributes redirectAttributes) {
        driverService.addDriver(dto);
        redirectAttributes.addFlashAttribute(FlashAttribute.SUCCESS_MESSAGE.getAttributeName(),
                messageService.getMessage("driver.add.success"));
        return REDIRECT_TO_DRIVERS_LIST;
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Driver driver = driverService.getDriverById(id);
        DriverRequestDTO driverRequestDTO = DriverRequestDTO.builder()
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .licenseNumber(driver.getLicenseNumber())
                .employmentDate(driver.getEmploymentDate())
                .build();

        model.addAttribute("driverRequestDTO", driverRequestDTO);
        model.addAttribute("driverId", id);
        return "driver/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDriver(@PathVariable UUID id,
                             @ModelAttribute("driverRequestDTO") DriverRequestDTO dto,
                             RedirectAttributes redirectAttributes) {
        driverService.editDriver(id, dto);
        redirectAttributes.addFlashAttribute(FlashAttribute.SUCCESS_MESSAGE.getAttributeName(),
                messageService.getMessage("driver.edit.success"));
        return REDIRECT_TO_DRIVERS_LIST;
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        driverService.deleteDriver(id);
        redirectAttributes.addFlashAttribute(FlashAttribute.SUCCESS_MESSAGE.getAttributeName(),
                messageService.getMessage("driver.delete.success"));
        return REDIRECT_TO_DRIVERS_LIST;
    }
}
