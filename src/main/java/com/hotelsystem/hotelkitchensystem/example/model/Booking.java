package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue
    private int bookingId;
    @Column(nullable = false)
    private String checkInDate;
    @Column(nullable = false)
    private String checkoutDate;
    @Column(nullable = false)
    private String meal;
    @Column(nullable = false)
    private int roomNo;


    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
