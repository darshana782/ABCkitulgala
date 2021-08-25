package com.hotelsystem.hotelkitchensystem.example.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelsystem.hotelkitchensystem.example.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_Table")
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;
    @Column(nullable = true)
    private String address;
    @Column(nullable = false)
    private String dob;
    @Column (nullable = false)
    private String nic;
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    @OneToOne
    @JsonIgnore
    private UserData userData;

    @OneToMany(targetEntity = Booking.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> booking;

}
