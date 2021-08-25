package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private RoomTypesRepository roomTypesRepository;

    public boolean checkIfRoomNoExists(int roomNo){
        if(roomsRepository.findByRoomNo(roomNo)!=null){
            return true;
        }
        return false;
    }

    public void addNewRoom(AddNewRoomRequest addNewRoomRequest){
        Rooms rooms = new Rooms();

        rooms.setRoomNo(addNewRoomRequest.getRoomNo());
        rooms.setAvailability(addNewRoomRequest.getAvailability());
        rooms.setRoomType(roomTypesRepository.findByRoomTypeID(addNewRoomRequest.getRoomTypeID()));

        roomsRepository.save(rooms);
    }
}
