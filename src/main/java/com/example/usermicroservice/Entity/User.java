package com.example.usermicroservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

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
    @NotBlank(message = "Please add your name here")
    private String  name;
    @NotBlank(message = "Please add your date of birth here")
    @Column(name = "DateOfBirth")
    private String  DateOfBirth;
    @NotBlank(message = "Please add your password here")
    @Column(name = "Password")
    private String  Password;
    @NotBlank(message = "Please add your email here")
    @Email(message = "Please provide a valid email address")
    @Column(name = "Email")
    private String  Email;
    @NotBlank(message = "Please add your address here")
    @Column(name = "Address")
    private String  Address;
    @NotBlank(message = "Please add your phone number here")
    @Column(name = "phonenumber")
    private int  PhoneNumber;
    @Column(name = "isAdmin")
    boolean isAdmin;




}
