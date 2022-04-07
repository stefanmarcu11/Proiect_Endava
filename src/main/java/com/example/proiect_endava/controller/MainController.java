package com.example.proiect_endava.controller;

import com.example.proiect_endava.entity.Appointment;
import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.service.AppointmentService;
import com.example.proiect_endava.service.CarService;
import com.example.proiect_endava.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final UserServiceImpl userService;
    private final CarService carService;
    private final AppointmentService appointmentService;

    public MainController(UserServiceImpl userService, CarService carService, AppointmentService appointmentService) {
        this.userService = userService;
        this.carService = carService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/intro")
    public String intro(){
        return "Intro";
    }

    @GetMapping("/")
    public String home(Model model) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.getByEmail(principal.getUsername()));

        List<Car> carList=carService.getCarList();
        model.addAttribute("carLis",carList);

        List<Appointment> appointmentList=appointmentService.getAppointmentList();
        model.addAttribute("appointmentLis",appointmentList);

        return "Dashboard";
    }


}
