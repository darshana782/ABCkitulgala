package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddDiscountsRequest;
import com.hotelsystem.hotelkitchensystem.example.model.RoomDiscounts;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomDiscountsRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomDiscountsService{
    @Autowired
    public RoomDiscountsRepository roomDiscountsRepository;

    @Autowired
    public RoomTypesRepository roomTypesRepository;

    public boolean checkIfDiscountExists(String fromDate){
        if (roomDiscountsRepository.findByFromDate(fromDate)!=null){
            return  true;
        }
        return false;
    }

    public boolean checkIfDiscountRoomTypeExists(RoomType roomType){
        if(roomDiscountsRepository.findByRoomType(roomType)!=null){
            return true;
        }
        return false;
    }

    public void addNewDiscounts(AddDiscountsRequest addDiscountsRequest) {
        RoomDiscounts roomDiscounts = new RoomDiscounts();

        roomDiscounts.setDiscountName(addDiscountsRequest.getDiscountName());
        roomDiscounts.setDescription(addDiscountsRequest.getDescription());
        roomDiscounts.setFromDate(addDiscountsRequest.getFromDate());
        roomDiscounts.setToDate(addDiscountsRequest.getToDate());
        roomDiscounts.setValue(addDiscountsRequest.getValue());
        roomDiscounts.setRoomType(roomTypesRepository.findByRoomTypeID(addDiscountsRequest.getRoomTypeID()));

        roomDiscountsRepository.save(roomDiscounts);
    }
}
