package com.example.proiect_endava.controller;


import com.example.proiect_endava.entity.AutoService;
import com.example.proiect_endava.entity.ServiceEntity;
import com.example.proiect_endava.entity.ServicesInAutoService;
import com.example.proiect_endava.service.AutoGarageService;
import com.example.proiect_endava.service.ServiceService;
import com.example.proiect_endava.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/autoServices")
public class AutoServiceController {

    private final AutoGarageService autoGarageService;
    private final ServiceService serviceService;
    private final UserServiceImpl userService;

    public AutoServiceController(AutoGarageService autoGarageService, ServiceService serviceService, UserServiceImpl userService) {
        this.autoGarageService = autoGarageService;
        this.serviceService = serviceService;
        this.userService = userService;
    }

    @GetMapping("/autoService")
    public String getAllAutoService(Model model){
        List<AutoService> autoService1=autoGarageService.getAutoServiceList();
        model.addAttribute("autoServiceList",autoService1);
        return "AutoServices.html";
    }

    @GetMapping("/addAutoService")
    public String addAutoService(Model model){
        AutoService autoService=new AutoService();
        model.addAttribute("newAutoService",autoService);

        List<AutoService> autoService2=autoGarageService.getAutoServiceList();
        model.addAttribute("autoServiceList",autoService2);

        ServiceEntity service=new ServiceEntity();
        ServicesInAutoService servicesInAutoService=new ServicesInAutoService();
        model.addAttribute("newService",service);
        model.addAttribute("newServiceInAutoService",servicesInAutoService);
        return "AddGarage.html";
    }

    @PostMapping(value = "/submitAutoService")
    public String submitAutoService(@ModelAttribute AutoService autoService){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        autoService.setUser(userService.getByEmail(principal.getUsername()));
        autoGarageService.saveAutoService(autoService);
        return "redirect:/";
    }

    @PostMapping(value="/deleteAutoService")
    public String deleteAutoService(@RequestParam("autoServiceId") Long id){
        autoGarageService.deleteAutoService(id);
        return"redirect:/autoService";
    }
    /*--------------------------------------------------------*/

    @PostMapping(value = "/submitService")
    public String submitService(@ModelAttribute ServiceEntity serviceEntity){

        serviceService.saveService(serviceEntity);
        return "redirect:/dashboard";
    }


}
