package com.hotelsystem.hotelkitchensystem.example.dto.request;

import com.hotelsystem.hotelkitchensystem.example.enums.BookingStatus;

public class BookingStatusRequest {
    private BookingStatus bookingStatus;

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
