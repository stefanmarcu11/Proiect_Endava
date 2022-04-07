package com.example.proiect_endava.service;


import com.example.proiect_endava.entity.Appointment;
import com.example.proiect_endava.entity.User;
import com.example.proiect_endava.exceptions.NotFoundException;
import com.example.proiect_endava.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentList(){

        return (List<Appointment>) appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) throws NotFoundException {

        return Optional.ofNullable(appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Appointment.class,id)));
    }

    public void saveAppointment(Appointment appointment){

        appointmentRepository.save(appointment);
    }
    public List<Appointment> getAppointmentByUser(Long id){
        return (List<Appointment>) appointmentRepository.findAppointmentByUser(id);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

}
