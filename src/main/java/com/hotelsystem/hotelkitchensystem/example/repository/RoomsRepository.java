package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
    Rooms findByRoomNo(int roomNo);

//    Rooms findByRoomNo(int roomNo);
}