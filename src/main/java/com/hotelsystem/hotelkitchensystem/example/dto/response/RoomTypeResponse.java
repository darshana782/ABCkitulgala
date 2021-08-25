package com.hotelsystem.hotelkitchensystem.example.dto.response;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;

public class RoomTypeResponse {
    private int roomTypeID;
    private RoomTypes roomTypes;
    private String description;
    private String image;
    private int no_of_rooms;
    private int no_of_persons;
    private int price;

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
}
