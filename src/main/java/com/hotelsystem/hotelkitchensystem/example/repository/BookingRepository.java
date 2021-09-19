package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findAllByRooms_RoomNo(int roomNo);
//    List<Booking> findAllByCustomer_CustomerId(int id);
}
