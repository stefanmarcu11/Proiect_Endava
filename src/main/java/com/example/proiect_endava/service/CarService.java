package com.example.proiect_endava.service;


import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.entity.User;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getCarList() {
        return (List<Car>) carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) throws NotFoundException {

        return Optional.ofNullable(carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Car.class, id)));
    }

    public List<Car> getCarByUser(Long id) {
        return (List<Car>) carRepository.findCarByUser(id);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

