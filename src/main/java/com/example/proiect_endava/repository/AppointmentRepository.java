package com.example.proiect_endava.repository;


import com.example.proiect_endava.entity.Appointment;
import com.example.proiect_endava.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Long> {

    Appointment findAppointmentByUser(Long id);
}
