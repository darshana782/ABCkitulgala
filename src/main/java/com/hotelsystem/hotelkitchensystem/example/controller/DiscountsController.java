package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddDiscountsRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.DiscountsResponse;
import com.hotelsystem.hotelkitchensystem.example.service.RoomDiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class DiscountsController {
    @Autowired
    private RoomDiscountsService roomDiscountsService;

    @PostMapping("/addDiscounts")
    public ResponseEntity addDiscounts(@RequestBody AddDiscountsRequest addDiscountsRequest){
        Date FromDate = addDiscountsRequest.getFromDate();
        Date ToDate = addDiscountsRequest.getToDate();
        int RoomTypeID = addDiscountsRequest.getRoomTypeID();
        String responseMsg;

        if(roomDiscountsService.checkIfDiscountExists(FromDate, ToDate, RoomTypeID)){
            responseMsg = "Discount already Exists for selected date";
        }else{
            roomDiscountsService.addNewDiscounts(addDiscountsRequest);
            responseMsg = "Successfully Added";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @GetMapping("/viewDiscounts")
    public List<DiscountsResponse> findAllDiscounts(){
        return roomDiscountsService.viewDiscounts();
    }
}
