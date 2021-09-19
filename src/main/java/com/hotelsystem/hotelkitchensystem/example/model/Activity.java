package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addactivity")

public class Activity {

    @Id
    @GeneratedValue
    private int activityId;
    private String activityName;
    private String checkInTime;
    private String checkOutTime;
    private String description;

}
