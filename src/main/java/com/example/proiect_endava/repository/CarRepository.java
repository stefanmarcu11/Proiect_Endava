package com.example.proiect_endava.repository;

import com.example.proiect_endava.entity.Car;
import com.example.proiect_endava.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {
    Car findCarByUser(Long id);
}
