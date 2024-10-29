package com.example.usermicroservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// GenerationType.IDENTITY is
    // used to auto increment the value of id
    @Column(name = "idUser")
    private Long id;
    @Column(name = "name")
    private String  name;
    @Column(name = "DateOfBirth")
    private String  DateOfBirth;
    @Column(name = "Password")
    private String  Password;
    @Column(name = "Email")
    private String  Email;
    @Column(name = "Address")
    private String  Address;
    @Column(name = "phonenumber")
    private int  PhoneNumber;
    @Column(name = "isAdmin")
    boolean isAdmin;




}
