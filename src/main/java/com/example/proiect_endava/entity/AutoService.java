package com.example.proiect_endava.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_autoservice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user")
    private User user;

//    @ManyToMany
//    @JoinTable(name="service_autoService",
//            joinColumns = @JoinColumn(name = "id_service", referencedColumnName = "id"), //corecta
//            inverseJoinColumns = @JoinColumn(name = "id_autoService",
//                    referencedColumnName = "id"))
//    private List<ServiceEntity> serviceList;


//    @ManyToMany
//    @JoinTable(name="appointment_autoService",
//            joinColumns = @JoinColumn(name = "id_appointment", referencedColumnName = "id"),//corecta
//            inverseJoinColumns = @JoinColumn(name = "id_autoService",
//                    referencedColumnName = "id"))
//    private List<Appointment> appointmentsAutoService;
//
//



}
