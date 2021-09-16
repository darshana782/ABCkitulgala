package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.UpdateRoomsResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
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

    public RoomResponse getRoomByRoomNo(int id){
       Rooms rooms = roomsRepository.findByRoomNo(id);
       RoomResponse roomDetails = new RoomResponse();
       RoomType roomType = roomTypesRepository.findByRooms(rooms);
       roomDetails.setRoomNo(rooms.getRoomNo());
       roomDetails.setRoomTypeID(roomType.getRoomTypeID());

       return roomDetails;
    }

    public void updateRoom(int id, UpdateRoomsResponse updateRoomsResponse){
        Rooms rooms = roomsRepository.findByRoomNo(id);
        rooms.setRoomType(roomTypesRepository.findByRoomTypeID(updateRoomsResponse.getRoomTypeID()));

        roomsRepository.save(rooms);
    }
    public void deleteRooms(int id){
        Rooms rooms = roomsRepository.findByRoomNo(id);
        roomsRepository.delete(rooms);
    }

    public List<Rooms> getRoomsByRoomTypes(RoomTypes roomTypes){
        RoomType roomType = roomTypesRepository.findByRoomTypes(roomTypes);
//        int x = roomType.getRoomTypeID();
        List<Rooms> rooms= roomsRepository.findAllByRoomType_RoomTypeID(roomType.getRoomTypeID());

        return rooms;
    }
}
