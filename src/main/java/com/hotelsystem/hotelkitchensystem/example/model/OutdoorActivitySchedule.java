package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outdoor_activity_schedule_table")
public class OutdoorActivitySchedule {

    @SequenceGenerator(
            name = "outdoor_activity_schedule_generator",
            sequenceName = "outdoor_activity_schedule",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "outdoor_activity_schedule"
    )
    private int outdoorActivityScheduleId;

    @ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="outdoorActivityId", nullable=false)
    private OutdoorActivity outdoorActivity;

    @Column(nullable = false)
    private ScheduleTimeSlot scheduledTimeSlot;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date scheduledDate;

    @Column(nullable = false)
    private boolean completed;

}
