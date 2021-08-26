package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddNewRoomRequest {
    private int roomNo;
    private int roomTypeID;

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
