package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddDiscountsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.DiscountsResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomResponse;
import com.hotelsystem.hotelkitchensystem.example.model.RoomDiscounts;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomDiscountsRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomDiscountsService{
    @Autowired
    public RoomDiscountsRepository roomDiscountsRepository;

    @Autowired
    public RoomTypesRepository roomTypesRepository;

    public boolean checkIfDiscountExists(Date fromDate, Date toDate, int roomTypeID){
        if (roomDiscountsRepository.findByFromDateAndToDateAndRoomType_RoomTypeID(fromDate,toDate,roomTypeID)!=null){
            return  true;
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

    public List<DiscountsResponse> viewDiscounts(){
        List<RoomDiscounts> allDetails = roomDiscountsRepository.findAll();
        List<DiscountsResponse> discountList = new ArrayList<>();

        for (RoomDiscounts roomDiscounts:allDetails){
            RoomType roomType = roomTypesRepository.findByDiscount(roomDiscounts);
            DiscountsResponse roomDiscountList =new DiscountsResponse();
            roomDiscountList.setDiscountID(roomDiscounts.getDiscountId());
            roomDiscountList.setDiscountName(roomDiscounts.getDiscountName());
            roomDiscountList.setDescription(roomDiscounts.getDescription());
            roomDiscountList.setFromDate(roomDiscounts.getFromDate());
            roomDiscountList.setToDate(roomDiscounts.getToDate());
            roomDiscountList.setValue(roomDiscounts.getValue());
            roomDiscountList.setRoomTypes(roomType.getRoomTypes());
            discountList.add(roomDiscountList);
        }
        return discountList;
    }
}
