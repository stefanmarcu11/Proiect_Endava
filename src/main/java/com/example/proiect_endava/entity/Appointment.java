package com.example.proiect_endava.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "t_appointment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(name="name")
//    private String name;
//
//    @Column(name="phone")
//    private String phone;

    @Column(name="date")
    private Date date;

//    @ManyToMany(mappedBy="appointmentsAutoService") //corecta
//    private List<AutoService> autoServiceList;

    @ManyToOne(cascade = CascadeType.ALL)//corecta
    @JoinColumn(name="user_appointment")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)//corecta
    @JoinColumn(name="car_appointment")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "services_autoservice_id")
    private ServicesInAutoService servicesInAutoService;

//    @ManyToMany
//    @JoinTable(name="service_appointment",
//            joinColumns = @JoinColumn(name="id_service",referencedColumnName = "id"),//corecta
//    inverseJoinColumns = @JoinColumn(name = "id_appointment",
//            referencedColumnName = "id"))
//    private List<ServiceEntity> serviceList;

}



