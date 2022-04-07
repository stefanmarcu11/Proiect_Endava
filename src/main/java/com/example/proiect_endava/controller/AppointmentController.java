package com.example.proiect_endava.controller;


import com.example.proiect_endava.entity.Appointment;
import com.example.proiect_endava.entity.AutoService;
import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.entity.ServiceEntity;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/appointments")
public class AppointmentController {

   private final AppointmentService appointmentService;
   private final AutoGarageService autoGarageService;
   private final ServiceService serviceService;
   private final CarService carService;
   private final UserServiceImpl userService;


    public AppointmentController(AppointmentService appointmentService, AutoGarageService autoGarageService,
                                 ServiceService serviceService, CarService carService, UserServiceImpl userService) {
        this.appointmentService = appointmentService;
        this.autoGarageService = autoGarageService;
        this.serviceService = serviceService;
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/appointments")
    public String getAppointments(Model model) throws NotFoundException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Appointment> appointmentList=appointmentService.getAppointmentByUser((userService.getByEmail(principal.getUsername()).getId()));
        model.addAttribute("appointmentList",appointmentList);

        return "Appointment";
    }


    @GetMapping(value="/addAppointment")
    public String addAppointmnet(Model model){

        Appointment appointment=new Appointment();
        model.addAttribute("appointment",appointment);

        List<AutoService> autoServiceList = autoGarageService.getAutoServiceList();
        model.addAttribute("autoServiceList",autoServiceList);

        List<Car> carList=carService.getCarList();
        model.addAttribute("carList",carList);
        return "AddAppointment.html";
    }

    @PostMapping(value = "/submitAppointment")
    public String submitAppointment(@ModelAttribute Appointment appointment){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        appointment.setUser(userService.getByEmail(principal.getUsername()));
        appointmentService.saveAppointment(appointment);
        return "redirect:/dashboard";
    }






}
