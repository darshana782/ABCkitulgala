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
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    private int emp_id;
    @Column (nullable = true)
    private String gender;

    @OneToOne
    @JsonIgnore
    private UserData userData;

    @OneToOne(targetEntity = StewardGuide.class, mappedBy = "employee", cascade = CascadeType.ALL)
    @JoinColumn(name = "sg_fk",referencedColumnName = "emp_id")
    private StewardGuide stewardGuide;
}

