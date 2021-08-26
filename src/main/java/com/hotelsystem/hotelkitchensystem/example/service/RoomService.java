package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomResponse;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        rooms.setRoomType(roomTypesRepository.findByRoomTypeID(addNewRoomRequest.getRoomTypeID()));

        roomsRepository.save(rooms);
    }

    public List<RoomResponse> viewRooms(){
        List<Rooms> allDetails = roomsRepository.findAll();
        List<RoomResponse> allRoomList = new ArrayList<>();
        for(Rooms rooms:allDetails){
            RoomType roomType = roomTypesRepository.findByRooms(rooms);
            RoomResponse roomList = new RoomResponse();
            roomList.setRoomNo(rooms.getRoomNo());
            roomList.setRoomTypeID(roomType.getRoomTypeID());
            roomList.setRoomTypes(roomType.getRoomTypes());
            allRoomList.add(roomList);
        }
        return allRoomList;
    }
}
