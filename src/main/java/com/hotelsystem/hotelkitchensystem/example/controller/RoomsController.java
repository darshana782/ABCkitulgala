package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.UpdateRoomsResponse;
import com.hotelsystem.hotelkitchensystem.example.model.Rooms;
import com.hotelsystem.hotelkitchensystem.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/viewRooms")
    public List<RoomResponse> findAllRooms(){
        return roomService.viewRooms();
    }

    @GetMapping("/viewUpdateRoomDetails/{id}")
    public RoomResponse findByRoomNo(@PathVariable int id){
        return roomService.getRoomByRoomNo(id);
    }

    @PutMapping("/updateRooms/{id}")
    public ResponseEntity updateRooms(@PathVariable int id,@RequestBody UpdateRoomsResponse updateRoomsResponse){
        String responseMsg;
        roomService.updateRoom(id,updateRoomsResponse);
        responseMsg = "Successfully Updated";
        return ResponseEntity.ok().body(responseMsg);
    }

    @DeleteMapping("/deleteRoom/{id}")
    public ResponseEntity deleteRoomByID(@PathVariable int id){
        roomService.deleteRooms(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
