package com.example.proiect_endava.controller;


import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.service.CarService;
import com.example.proiect_endava.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final UserServiceImpl userService;

    @GetMapping("/car")
    public String getAllCars(Model model){
        List<Car> car=carService.getCarList();
        model.addAttribute("carList",car);
        return "Cars.html";
    }

    //@RequestMapping(value="/addCar",method = { RequestMethod.GET, RequestMethod.POST})
    @GetMapping(value="/addcar")
    public String addCar(Model model){
        Car car=new Car();
        model.addAttribute("newcar",car);
        return "AddCar.html";
    }

    @PostMapping(value = "/submitCar")
    public String submitCar(@ModelAttribute Car cars){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cars.setUser(userService.getByEmail(principal.getUsername()));
        carService.saveCar(cars);
        return "redirect:/";
    }

    @PostMapping(value="/deleteCar")
    public String deleteCar(@RequestParam("carId") Long id){
        carService.deleteCar(id);
        return"redirect:/car";
    }
     @PostMapping(value="/editCar")
     public String editCar(@ModelAttribute("editedCar") Car car){
        carService.saveCar(car);
        return "redirect:/car";
    }

    @GetMapping(value="/findOneCar")
    @ResponseBody
    public Optional<Car> findOneCar(Long id) throws NotFoundException {
        return carService.getCarById(id);
    }
}
