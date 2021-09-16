package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
    Rooms findByRoomNo(int roomNo);
    List<Rooms> findAllByRoomType_RoomTypeID(int roomTypeID);
//    @Query(value = "SELECT x.roomNo FROM Rooms x INNER JOIN RoomType y ON y.roomTypeID = RoomType_roomTypeID WHERE y.roomTypes=?1", nativeQuery = true)
//    List<Rooms> findByRoomTypes(RoomTypes roomTypes);
}