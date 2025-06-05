package com.fleetmanager.vehiclefleetmanagement.handler;

import com.fleetmanager.vehiclefleetmanagement.entity.Vehicle;
import com.fleetmanager.vehiclefleetmanagement.enums.VehicleSearchContext;
import com.fleetmanager.vehiclefleetmanagement.enums.VehicleSearchType;
import com.fleetmanager.vehiclefleetmanagement.service.MessageService;
import com.fleetmanager.vehiclefleetmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleSearchHandler {
    private final VehicleService vehicleService;
    private final MessageService messageService;

    public String handleVehicleSearch(VehicleSearchType type, String query,
                                      VehicleSearchContext context,
                                      RedirectAttributes redirectAttributes) {
        try {
            log.debug("Searching for vehicle by {}: {} (context: {})", type, query, context);
            return vehicleService.searchByType(type, query)
                    .map(vehicle -> handleSuccessfulSearch(vehicle, context, redirectAttributes))
                    .orElse(handleFailedSearch(context, redirectAttributes));
        } catch (IllegalArgumentException e) {
            log.error("Search error: {}", e.getMessage());
            return handleSearchError(context, redirectAttributes, e);
        }
    }


    private String handleSuccessfulSearch(Vehicle vehicle, VehicleSearchContext context,
                                          RedirectAttributes redirectAttributes) {
        log.info("Vehicle found: {} ({})", vehicle.getRegistrationNumber(), vehicle.getId());

        redirectAttributes.addFlashAttribute("successMessage",
                "Vehicle found: " + vehicle.getRegistrationNumber());

        return buildContextRedirect(vehicle, context);
    }


    private String handleFailedSearch(VehicleSearchContext context, RedirectAttributes redirectAttributes) {
        log.info("No vehicle found matching search criteria (context: {})", context);

        redirectAttributes.addFlashAttribute("warningMessage", messageService.getMessage("vehicle.search.notFound"));

        return buildErrorRedirect(context);
    }

    private String handleSearchError(VehicleSearchContext context, RedirectAttributes redirectAttributes, Exception e) {
        log.error("Search error occurred (context: {}): {}", context, e.getMessage());

        String errorMessage = getContextErrorMessage(context);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

        return buildErrorRedirect(context);
    }


    private String buildContextRedirect(Vehicle vehicle, VehicleSearchContext context) {
        return switch (context) {
            case DETAILS -> "redirect:/vehicles/" + vehicle.getId();
            case FUEL_LOGS -> "redirect:/fuel-logs/vehicle/" + vehicle.getId();
        };
    }

    private String buildErrorRedirect(VehicleSearchContext context) {
        return switch (context) {
            case DETAILS -> "redirect:/vehicles/search-page";
            case FUEL_LOGS -> "redirect:/fuel-logs/search-page";
            case null -> "redirect:/vehicles/search-page";
        };
    }

    private String getContextErrorMessage(VehicleSearchContext context) {
        return switch (context) {
            case DETAILS -> messageService.getMessage("vehicle.search.error");
            case FUEL_LOGS -> messageService.getMessage("vehicle.search.error.fuelLogs");
            case null -> messageService.getMessage("vehicle.search.error");
        };
    }
}
