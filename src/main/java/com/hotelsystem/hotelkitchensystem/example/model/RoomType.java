package com.hotelsystem.hotelkitchensystem.example.model;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="RoomType")

public class RoomType {
    @Id
    private int roomTypeID;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomTypes roomTypes;
    @Column (nullable = true)
    private String description;
    @Column (nullable = true)
    private String image;
    @Column (nullable = false)
    private int no_of_rooms;
    @Column (nullable = false)
    private int no_of_persons;
    @Column (nullable = false)
    private int price;

    @OneToMany (targetEntity = Rooms.class, mappedBy = "roomType", cascade = CascadeType.ALL)
    private List<Rooms> rooms;

}
