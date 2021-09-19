package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.BookingRequest;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.Booking;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getroomnumbers/{roomTypes}/{date1}/{date2}")
    public int getRoomNumbers(@PathVariable RoomTypes roomTypes,@PathVariable ("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,@PathVariable ("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
        return bookingService.getRoomNoByRoomType(roomTypes,date1,date2);
    }

    @PostMapping("/addbooking")
    public ResponseEntity addBooking(@RequestBody BookingRequest bookingRequest){
        String responseMsg;
        try {
            bookingService.addBooking(bookingRequest);
            responseMsg = "Booking successfull";
            return ResponseEntity.ok().body(responseMsg);
        }catch (Exception e){
            responseMsg = "Rooms Unavailable";
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
}
