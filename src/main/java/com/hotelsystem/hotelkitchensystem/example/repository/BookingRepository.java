package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findAllByRooms_RoomNo(int roomNo);

    List<Booking> findAllByCustomer_CustomerId(int id);
    Booking findByBookingId(int id);

//    List<Booking> findAllByCustomer_CustomerId(int id);

    @Query(value = "SELECT b FROM Booking b WHERE b.customer.customerId=?1 order by b.checkInDate")
    List<Booking> findAllByCustomer_CustomerIdOrderByDate(int customerId);

    @Query(value = "SELECT b FROM Booking b WHERE b.customer.customerId=?1 AND b.bookingStatus=?2")
    List<Booking> findAllByCustomer_CustomerIdAndStatus(int customerId, BookingStatus status);
}
