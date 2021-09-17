package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getroomnumbers/{roomTypes}")
    public List<Booking> getRoomNumbers(@PathVariable RoomTypes roomTypes){
        return bookingService.getRoomNoByRoomType(roomTypes);
    }
}
