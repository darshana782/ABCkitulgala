package com.hotelsystem.hotelkitchensystem.example.repository;

import com.hotelsystem.hotelkitchensystem.example.model.RoomDiscounts;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDiscountsRepository extends JpaRepository<RoomDiscounts,Integer> {
    RoomDiscounts findByDiscountId(int discountId);
    RoomDiscounts findByFromDate(String fromDate);

}
