package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.RoomDiscounts;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface RoomDiscountsRepository extends JpaRepository<RoomDiscounts,Integer> {
    RoomDiscounts findByDiscountId(int discountId);
//    @Query(value = "SELECT * FROM ;d=?1 AND i.invoice_date BETWEEN CURDATE()?2 - INTERVAL 1 MONTH AND CURDATE() " , nativeQuery = true)
//    RoomDiscounts<List> findByFromDatebyType(Date fromDate,Date todate);

    RoomDiscounts findByFromDateAndToDateAndRoomType_RoomTypeID(Date fromDate, Date todate, int roomTypeID);
}
