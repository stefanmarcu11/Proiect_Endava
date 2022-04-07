package com.example.proiect_endava.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_cars")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="number_plate")
    private String numberPlate;

    @Column(name="car_brand")
    private String carBrand;

    @Column(name="model")
    private String model;

    @Column(name="power")
    private int power;

    @Column(name= "type")
    private String type;

    @Column(name= "year")
    private int year;

    @Column(name="consumption")
    private double consumption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user")
    private User user;

    @OneToMany(mappedBy = "car")
    private List<Appointment> appointmentList;

}
