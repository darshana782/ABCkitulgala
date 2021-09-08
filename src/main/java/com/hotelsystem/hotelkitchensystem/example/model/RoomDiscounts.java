package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

public class RoomDiscounts {
    @Id
    @GeneratedValue
    private int discountId;
    @Column
    private String discountName;
    @Column
    private String description;
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date fromDate;
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date toDate;
    @Column
    private String value;

    @ManyToOne
    @JsonIgnore
    private RoomType roomType;
}
