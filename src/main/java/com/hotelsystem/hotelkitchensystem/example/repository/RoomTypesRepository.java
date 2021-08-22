package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypesRepository extends JpaRepository<RoomType, Integer> {
    RoomType findByRoomTypeID(int roomTypeID);
    RoomType findByRoomTypes(RoomTypes roomTypes);
}
