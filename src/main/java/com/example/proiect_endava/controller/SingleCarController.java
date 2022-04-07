package com.example.proiect_endava.controller;


import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/car/{id}")
public class SingleCarController {

    @Autowired
    private CarService carService;

    @ModelAttribute("carId")
    protected Car modelCar(@PathVariable Long id) throws NotFoundException {
        Optional<Car> carOpt = carService.getCarById(id);
        if(carOpt.isPresent()) {
            return carOpt.get();
        } else {
            throw new NotFoundException(Car.class,id);
        }
    }

    @GetMapping
    public String editSingleCar() {

        return "EditSingleCar.html";
    }

    @PostMapping
    public String save(@Validated Car car, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return "EditSingleCar.html";
        }
        try {
            carService.saveCar(car);
            return "redirect:/car";
        } catch (Exception e ) {
            return "redirect:/car/{id}";
        }
    }
}
