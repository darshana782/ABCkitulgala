package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.AddDiscountsRequest;
import com.hotelsystem.hotelkitchensystem.example.service.RoomDiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class DiscountsController {
    @Autowired
    private RoomDiscountsService roomDiscountsService;

    @PostMapping("/addDiscounts")
    public ResponseEntity addDiscounts(@RequestBody AddDiscountsRequest addDiscountsRequest){
        Date FromDate = addDiscountsRequest.getFromDate();
        String responseMsg;

        if(roomDiscountsService.checkIfDiscountExists(FromDate)){
            responseMsg = "Discount already Exists";
        }else{
            roomDiscountsService.addNewDiscounts(addDiscountsRequest);
            responseMsg = "Successfully Added";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }
}
