package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomTypeRequest;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
//import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {
    @Autowired
    private RoomTypesRepository roomTypesRepository;

//    @Autowired
//    private RoomsRepository roomsRepository;

    public boolean checkIfRoomTypeIdExists(int roomTypeID ){
        if(roomTypesRepository.findByRoomTypeID(roomTypeID) != null){
            return true;
        }
        return false;
    }
    public boolean checkIfRoomTypesExists(RoomTypes roomTypes){
        if(roomTypesRepository.findByRoomTypes(roomTypes) != null){
            return true;
        }
        return false;
    }

    public void addRoomTypes(AddNewRoomTypeRequest addNewRoomTypeRequest){
        RoomType roomType = new RoomType();

        //set data for room type object
        roomType.setRoomTypeID(addNewRoomTypeRequest.getRoomTypeID());
        roomType.setRoomTypes(addNewRoomTypeRequest.getRoomTypes());
        roomType.setDescription(addNewRoomTypeRequest.getDescription());
        roomType.setImage(addNewRoomTypeRequest.getImage());
        roomType.setNo_of_rooms(addNewRoomTypeRequest.getNo_of_rooms());
        roomType.setNo_of_persons(addNewRoomTypeRequest.getNo_of_persons());
        roomType.setPrice(addNewRoomTypeRequest.getPrice());
        roomTypesRepository.save(roomType);
    }
}
