package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outdoor_activity_table")
public class OutdoorActivity {

    @SequenceGenerator(
            name = "outdoor_activity_generator",
            sequenceName = "outdoor_activity",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "outdoor_activity"
    )
    private int outdoorActivityId;

    @Column(nullable = false)
    private String outdoorActivityName;

}
