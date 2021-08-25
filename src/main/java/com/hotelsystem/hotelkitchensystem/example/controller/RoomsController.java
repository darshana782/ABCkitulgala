package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomRequest;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class RoomsController {
    @Autowired
    private RoomService roomService;



    @PostMapping("/addRooms")
    public ResponseEntity addRoom(@RequestBody AddNewRoomRequest addNewRoomRequest){
        int RoomNo = addNewRoomRequest.getRoomNo();
        String responseMsg;

        if(roomService.checkIfRoomNoExists(RoomNo)){
            responseMsg="Room No Already Exists";
        }else{
            roomService.addNewRoom(addNewRoomRequest);
            responseMsg="Successfully Added";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
}
