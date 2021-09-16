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
@Table (name = "rooms")
public class Rooms {
    @Id
    private int roomNo;
    @Column(nullable = true)
    private int availability;
//    private  int roomTypeId;
//
//    public int getRoomTypeId() {
//        return roomTypeId;
//    }

    @ManyToOne
    @JsonIgnore
    private RoomType roomType;
}
