package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomTypeRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.GetRoomTypesResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomTypeResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.model.RoomType;
import com.hotelsystem.hotelkitchensystem.example.repository.RoomTypesRepository;
//import com.hotelsystem.hotelkitchensystem.example.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkIfRoomTypeIDAlreadyUsed(int roomTypeID, RoomTypes roomTypes){
        if (roomTypesRepository.findByRoomTypeID(roomTypeID) == roomTypesRepository.findByRoomTypes(roomTypes)){
            return false;
        }else if (roomTypesRepository.findByRoomTypeID(roomTypeID)!=null){
            return true;
        }else return false;
    }

    public boolean checkIfRoomTypeAlreadyGiven(int roomTypeID, RoomTypes roomTypes){
        if (roomTypesRepository.findByRoomTypes(roomTypes)== roomTypesRepository.findByRoomTypeID(roomTypeID)){
            return false;
        }else if (roomTypesRepository.findByRoomTypes(roomTypes)!=null){
            return true;
        }else return false;
    }

    public void addRoomTypes(AddNewRoomTypeRequest addNewRoomTypeRequest){
        RoomType roomType = new RoomType();

        //set Another data for room type object
        roomType.setRoomTypeID(addNewRoomTypeRequest.getRoomTypeID());
        roomType.setRoomTypes(addNewRoomTypeRequest.getRoomTypes());
        roomType.setDescription(addNewRoomTypeRequest.getDescription());
        roomType.setImage(addNewRoomTypeRequest.getImage());
        roomType.setNo_of_rooms(addNewRoomTypeRequest.getNo_of_rooms());
        roomType.setNo_of_persons(addNewRoomTypeRequest.getNo_of_persons());
        roomType.setPrice(addNewRoomTypeRequest.getPrice());
        roomTypesRepository.save(roomType);
    }

    public List<RoomTypeResponse> viewRoomType(){
        List<RoomType> allDetails = roomTypesRepository.findAll();
        List<RoomTypeResponse> roomTypeList= new ArrayList<RoomTypeResponse>();
        for(RoomType roomType:allDetails){
            RoomTypeResponse typeList = new RoomTypeResponse();
            typeList.setRoomTypeID(roomType.getRoomTypeID());
            typeList.setRoomTypes(roomType.getRoomTypes());
            typeList.setDescription(roomType.getDescription());
            typeList.setImage(roomType.getImage());
            typeList.setNo_of_rooms(roomType.getNo_of_rooms());
            typeList.setNo_of_persons(roomType.getNo_of_persons());
            typeList.setPrice(roomType.getPrice());
            roomTypeList.add(typeList);
        }
        return roomTypeList;
    }

    public RoomTypeResponse getRoomTypeByID(int id){
        RoomType roomType = roomTypesRepository.findByRoomTypeID(id);
        RoomTypeResponse roomTypeDetails = new RoomTypeResponse();
        roomTypeDetails.setRoomTypeID(roomType.getRoomTypeID());
        roomTypeDetails.setRoomTypes(roomType.getRoomTypes());
        roomTypeDetails.setDescription(roomType.getDescription());
        roomTypeDetails.setImage(roomType.getImage());
        roomTypeDetails.setNo_of_rooms(roomType.getNo_of_rooms());
        roomTypeDetails.setNo_of_persons(roomType.getNo_of_persons());
        roomTypeDetails.setPrice(roomType.getPrice());

        return roomTypeDetails;
    }

    public void UpdateRoomType(int id, RoomTypeResponse roomTypeResponse){
        RoomType roomType = roomTypesRepository.findByRoomTypeID(id);

        roomType.setRoomTypeID(roomTypeResponse.getRoomTypeID());
        roomType.setRoomTypes(roomTypeResponse.getRoomTypes());
        roomType.setDescription(roomTypeResponse.getDescription());
        roomType.setImage(roomTypeResponse.getImage());
        roomType.setNo_of_rooms(roomTypeResponse.getNo_of_rooms());
        roomType.setNo_of_persons(roomTypeResponse.getNo_of_persons());
        roomType.setPrice(roomTypeResponse.getPrice());
        roomTypesRepository.save(roomType);
    }

    public void deleteRoomType(int id){
        RoomType roomType = roomTypesRepository.findByRoomTypeID(id);
        roomTypesRepository.delete(roomType);
    }

    public List<GetRoomTypesResponse> viewRoomTypes(){
        List<RoomType> roomTypesList = roomTypesRepository.findAll();
        List<GetRoomTypesResponse> typeList = new ArrayList<GetRoomTypesResponse>();

        for (RoomType roomType:roomTypesList){
            GetRoomTypesResponse mylist = new GetRoomTypesResponse();
            mylist.setRoomTypes(roomType.getRoomTypes());
            typeList.add(mylist);
        }
        return typeList;
    }

}
