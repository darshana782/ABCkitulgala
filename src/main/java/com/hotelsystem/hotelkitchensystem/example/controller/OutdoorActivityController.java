package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.OutdoorActivityRequest;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivity;
import com.hotelsystem.hotelkitchensystem.example.service.OutdoorActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outdoor-activities")
public class OutdoorActivityController {

    @Autowired
    private final OutdoorActivityService outdoorActivityService;

    public OutdoorActivityController(OutdoorActivityService outdoorActivityService) {
        this.outdoorActivityService = outdoorActivityService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllOutdoorActivities(){
        try{
            List<OutdoorActivity> outdoorActivityList = outdoorActivityService.getAllOutdoorActivities();
            if(outdoorActivityList != null){
                return ResponseEntity.status(HttpStatus.OK).body(outdoorActivityList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activities Found");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @PostMapping
    public ResponseEntity<Object> addNewOutdoorActivity(@RequestBody OutdoorActivityRequest outdoorActivityRequest){
        try{
            if(!outdoorActivityService.checkIfOutdoorActivityExists(outdoorActivityRequest)){
                outdoorActivityService.addOutdoorActivity(outdoorActivityRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Outdoor Activity Already Exists!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteOutdoorActivity(@RequestBody OutdoorActivityRequest outdoorActivityRequest){
        try{
            if(!outdoorActivityService.checkIfOutdoorActivityExists(outdoorActivityRequest)){
                outdoorActivityService.deleteOutdoorActivityById(outdoorActivityRequest);
                return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
            }
            return ResponseEntity.status(HttpStatus.OK).body("No Any Outdoor Activities Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

}
