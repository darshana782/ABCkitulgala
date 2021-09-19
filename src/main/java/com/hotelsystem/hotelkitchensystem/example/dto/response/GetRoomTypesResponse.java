package com.hotelsystem.hotelkitchensystem.example.dto.response;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;

public class GetRoomTypesResponse {
   private RoomTypes roomTypes;


    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomTypes roomTypes) {
        this.roomTypes = roomTypes;
    }
}
