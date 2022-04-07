package com.example.proiect_endava.controller;


import com.example.proiect_endava.entity.AutoService;
import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.service.ServiceService;
import com.example.proiect_endava.service.AutoGarageService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/autoService/{id}")
public class SingleAutoServiceController {

    private final AutoGarageService autoGarageService;
    private final ServiceService serviceService;

    public SingleAutoServiceController(AutoGarageService autoGarageService, ServiceService serviceService) {
        this.autoGarageService = autoGarageService;
        this.serviceService = serviceService;
    }


    @ModelAttribute("autoServiceId")
    protected AutoService modelAutoService(@PathVariable Long id) throws NotFoundException {
        Optional <AutoService> autoServiceOpt = autoGarageService.findAutoServiceById(id);
        if(autoServiceOpt.isPresent()) {

            return autoServiceOpt.get();
        } else {
            throw new NotFoundException(Car.class,id);
        }

    }

    @GetMapping
    public String editSingleAutoSerivce() {
        return "EditAutoService.html";
    }

    @PostMapping
    public String update(@Validated AutoService autoService, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return "EditSingleCar.html";
        }
        try {
            autoGarageService.saveAutoService(autoService);
            return "redirect:/autoService";
        } catch (Exception e ) {
            return "redirect:/autoService/{id}";
        }
    }

}
