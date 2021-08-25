package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddNewRoomTypeRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.RoomTypeResponse;
import com.hotelsystem.hotelkitchensystem.example.enums.RoomTypes;
import com.hotelsystem.hotelkitchensystem.example.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping("/addRoomType")
    public ResponseEntity addRoomType(@RequestBody AddNewRoomTypeRequest addNewRoomTypeRequest) {
        int roomTypeID = addNewRoomTypeRequest.getRoomTypeID();
        RoomTypes roomTypes = addNewRoomTypeRequest.getRoomTypes();
        String responseMsg;

        if (roomTypeService.checkIfRoomTypeIdExists (roomTypeID)) {
            responseMsg = "This Id already exisis";
        }
        else if (roomTypeService.checkIfRoomTypesExists(roomTypes)){
            responseMsg="This room type already exists";
        }else {
            roomTypeService.addRoomTypes(addNewRoomTypeRequest);
            responseMsg="New room type successfully added";
            return ResponseEntity.ok().body(responseMsg);

        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @GetMapping("/viewRoomTypes")
    public List<RoomTypeResponse> findAllEmployeess(){
        return roomTypeService.viewRoomType();
    }

}
