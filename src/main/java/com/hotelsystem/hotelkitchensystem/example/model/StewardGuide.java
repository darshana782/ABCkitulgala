package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StewardGuide {
    @Id
    @GeneratedValue
    private int SG_id;
    @Column(nullable = false)
    private String availability = "AVAILABLE";

    @OneToOne
    @JsonIgnore
    private Employee employee;
}
//hfdhfhfhf