package com.example.proiect_endava.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_services_autoservice")
public class ServicesInAutoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="autoservice_id")
    private AutoService autoService;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
}
