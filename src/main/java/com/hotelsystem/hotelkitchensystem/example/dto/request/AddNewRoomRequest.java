package com.hotelsystem.hotelkitchensystem.example.dto.request;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;

public class AddNewRoomRequest {
    private int roomNo;
    private int roomTypeID;
    private RoomTypes roomTypes;

    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomTypes roomTypes) {
        this.roomTypes = roomTypes;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }
}
