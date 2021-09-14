package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
    Rooms findByRoomNo(int roomNo);
    Rooms findByRoomType(RoomTypes roomTypes);

//    Rooms findByRoomNo(int roomNo);
}