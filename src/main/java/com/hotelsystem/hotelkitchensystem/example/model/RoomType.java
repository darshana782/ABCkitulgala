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

    @OneToMany(targetEntity = RoomDiscounts.class, mappedBy = "roomType", cascade = CascadeType.ALL)
    private List<RoomDiscounts> discount;

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomTypes roomTypes) {
        this.roomTypes = roomTypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }

    public int getNo_of_persons() {
        return no_of_persons;
    }

    public void setNo_of_persons(int no_of_persons) {
        this.no_of_persons = no_of_persons;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(List<Rooms> rooms) {
        this.rooms = rooms;
    }

    public List<RoomDiscounts> getDiscount() {
        return discount;
    }

    public void setDiscount(List<RoomDiscounts> discount) {
        this.discount = discount;
    }
}
