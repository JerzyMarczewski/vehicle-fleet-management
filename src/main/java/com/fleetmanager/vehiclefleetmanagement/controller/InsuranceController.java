package com.fleetmanager.vehiclefleetmanagement.controller;

import com.fleetmanager.vehiclefleetmanagement.dto.CreateInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.dto.EditInsuranceRequestDTO;
import com.fleetmanager.vehiclefleetmanagement.entity.Insurance;
import com.fleetmanager.vehiclefleetmanagement.mapper.InsuranceMapper;
import com.fleetmanager.vehiclefleetmanagement.service.InsuranceService;
import com.fleetmanager.vehiclefleetmanagement.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
@RequestMapping("/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final InsuranceMapper insuranceMapper;
    private final MessageService messageService;

    public InsuranceController(InsuranceService insuranceService, InsuranceMapper insuranceMapper, MessageService messageService) {
        this.insuranceService = insuranceService;
        this.insuranceMapper = insuranceMapper;
        this.messageService = messageService;
    }

    @GetMapping("/search-page")
    public String showSearchPage() {
        return "insurance/search";
    }

    @GetMapping("/search")
    public String searchInsurance(
            @RequestParam String searchType,
            @RequestParam String searchValue,
            @RequestParam(required = false) String returnUrl,
            @RequestParam(required = false) String selectAction,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Optional<Insurance> result = insuranceService.searchByPolicyNumber(searchType, searchValue);

            if (result.isPresent()) {
                if (selectAction != null && !selectAction.isEmpty()) {
                    model.addAttribute("returnUrl", returnUrl);
                    model.addAttribute("selectAction", selectAction);
                    model.addAttribute("insurance", result.get());
                    return "fragments/insurance-search :: insuranceSearch";
                }
                return "redirect:/insurances/" + result.get().getId();
            } else {
                model.addAttribute("noResult", true);
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "fragments/insurance-search :: insuranceSearch";
    }

    @GetMapping
    public String showInsurance(Model model) {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        model.addAttribute("insurances", insurances);
        return "insurance/list";
    }

    @GetMapping("/{id}")
    public String getInsuranceById(@PathVariable UUID id, Model model) {
        Insurance insurance = insuranceService.getInsuranceById(id);
        model.addAttribute("insurance", insurance);
        return "insurance/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("createInsuranceRequestDTO", new CreateInsuranceRequestDTO());
        return "insurance/create";
    }

    @PostMapping("/create")
    public String createInsurance(@ModelAttribute("createInsuranceRequestDTO") CreateInsuranceRequestDTO dto) {
        insuranceService.createInsurance(dto);
        return "redirect:/insurances";

    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Insurance insurance = insuranceService.getInsuranceById(id);
        model.addAttribute("editInsuranceRequestDTO", insuranceMapper.toEditDTO(insurance));
        model.addAttribute("insuranceId", id);
        return "insurance/edit";
    }

    @PostMapping("/{id}/edit")
    public String editInsurance(
    @PathVariable UUID id, 
    @ModelAttribute @DateTimeFormat(pattern = "yyyy-MM-dd") EditInsuranceRequestDTO dto
) {
    insuranceService.editInsurance(id, dto);
    return "redirect:/insurances/" + id;
}

    @DeleteMapping("/{id}")
    public String deleteInsurance(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        insuranceService.deleteInsurance(id);
        redirectAttributes.addFlashAttribute("message", "Insurance has been deleted");
        return "redirect:/insurances";

    }
}