package com.example.proiect_endava.entity;

import com.example.proiect_endava.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="authority_id")
    private Authority role;

    public User(String firstName, String lastName, String email, String phone, String password, Authority role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role=role;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
