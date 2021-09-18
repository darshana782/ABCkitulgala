package com.hotelsystem.hotelkitchensystem.example.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelsystem.hotelkitchensystem.example.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date dob;
    @Column (nullable = false)
    private String nic;
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    @OneToOne
    @JsonIgnore
    private UserData userData;

    @OneToMany(targetEntity = Booking.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> booking;

    @OneToMany(targetEntity = ReviewFeedback.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ReviewFeedback> reviewFeedback;

}
