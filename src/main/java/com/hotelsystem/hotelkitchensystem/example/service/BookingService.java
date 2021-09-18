package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.repository.BookingRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private RoomTypesRepository roomTypesRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getRoomNoByRoomType(RoomTypes roomTypes){
        RoomType roomType = roomTypesRepository.findByRoomTypes(roomTypes);
        List<Rooms> rooms= roomsRepository.findAllByRoomType_RoomTypeID(roomType.getRoomTypeID());
        List<Booking> bookings = new ArrayList<Booking>();

        for(Rooms rooms1:rooms){
            List<Booking> bookings1 = bookingRepository.findAllByRooms_RoomNo(rooms1.getRoomNo());
            bookings.addAll(bookings1);
        }
        return bookings;
    }
}
