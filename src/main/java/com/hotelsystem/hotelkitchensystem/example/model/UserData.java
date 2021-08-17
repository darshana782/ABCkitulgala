package com.hotelsystem.hotelkitchensystem.example.model;


import com.hotelsystem.hotelkitchensystem.example.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UserData_Table")
public class UserData   {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(nullable = false,unique = true)
    private String contactNo;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;

    @OneToOne(targetEntity = Customer.class, mappedBy = "userData", cascade = CascadeType.ALL)
    private Customer customer;

}
