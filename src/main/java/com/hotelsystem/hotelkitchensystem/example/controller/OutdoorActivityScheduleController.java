package com.hotelsystem.hotelkitchensystem.example.controller;

import com.hotelsystem.hotelkitchensystem.example.dto.request.OutdoorActivityScheduleRequest;
import com.hotelsystem.hotelkitchensystem.example.dto.response.AvailableOutdoorActivityResponse;
import com.hotelsystem.hotelkitchensystem.example.dto.response.OutdoorActivityResponse;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivitySchedule;
import com.hotelsystem.hotelkitchensystem.example.service.OutdoorActivityScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outdoor-activity-schedules")
public class OutdoorActivityScheduleController {

    @Autowired
    private final OutdoorActivityScheduleService outdoorActivityScheduleService;

    public OutdoorActivityScheduleController(OutdoorActivityScheduleService outdoorActivityScheduleService) {
        this.outdoorActivityScheduleService = outdoorActivityScheduleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllOutdoorActivitySchedules(){
        try{
            List<OutdoorActivitySchedule> outdoorActivityScheduleList = outdoorActivityScheduleService.getAllOutdoorActivitySchedules();
            if(outdoorActivityScheduleList != null){
                return ResponseEntity.status(HttpStatus.OK).body(outdoorActivityScheduleList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @PostMapping("/available")
    public ResponseEntity<Object> getAllAvailableOutdoorActivitySchedules(@RequestBody OutdoorActivityScheduleRequest outdoorActivitySchedule){
        try{
            List<AvailableOutdoorActivityResponse> outdoorActivityScheduleList = outdoorActivityScheduleService
                    .getAllAvailableOutdoorActivitySchedulesByDay(outdoorActivitySchedule);
            if(outdoorActivityScheduleList != null){
                return ResponseEntity.status(HttpStatus.OK).body(outdoorActivityScheduleList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createNewOutdoorActivitySchedule(@RequestBody OutdoorActivityScheduleRequest outdoorActivityScheduleRequest){
        try{
            if(outdoorActivityScheduleService.checkIfOutdoorActivityScheduleTimeslotIsFilled(outdoorActivityScheduleRequest)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Timeslot Already Filled");
            }else{
                outdoorActivityScheduleService.createOutdoorActivitySchedule(outdoorActivityScheduleRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @PostMapping("/customer-schedules")
    public ResponseEntity<Object> getAllOutdoorActivitySchedulesByCustomer(@RequestBody OutdoorActivityScheduleRequest outdoorActivityScheduleRequest){
        try{
            List<OutdoorActivitySchedule> outdoorActivityScheduleList = outdoorActivityScheduleService.getAllOutdoorActivitySchedulesByCustomerId(outdoorActivityScheduleRequest);
            if(outdoorActivityScheduleList != null){
                return ResponseEntity.status(HttpStatus.OK).body(outdoorActivityScheduleList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @PatchMapping("/customer-schedules/set-completed")
    public ResponseEntity<Object> setOutdoorActivityScheduleCompleted(@RequestBody OutdoorActivityScheduleRequest outdoorActivitySchedule){
        try{
            boolean outdoorActivityScheduleList = outdoorActivityScheduleService.setOutdoorActivityScheduleCompleted(outdoorActivitySchedule);
            if(outdoorActivityScheduleList){
                return ResponseEntity.status(HttpStatus.OK).body("Successfully Set Completed");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @GetMapping("/customer-schedules/completed")
    public ResponseEntity<Object> getAllCompletedOutdoorActivitySchedules(){
        try{
            List<OutdoorActivitySchedule> completedOutdoorActivityScheduleList = outdoorActivityScheduleService.getAllCompletedOutdoorActivitySchedules();
            if(completedOutdoorActivityScheduleList != null){
                return ResponseEntity.status(HttpStatus.OK).body(completedOutdoorActivityScheduleList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @GetMapping("/customer-schedules/in-complete")
    public ResponseEntity<Object> getAllInCompletedOutdoorActivitySchedules(){
        try{
            List<OutdoorActivitySchedule> inCompletedOutdoorActivityScheduleList = outdoorActivityScheduleService.getAllInCompletedOutdoorActivitySchedules();
            if(inCompletedOutdoorActivityScheduleList != null){
                return ResponseEntity.status(HttpStatus.OK).body(inCompletedOutdoorActivityScheduleList);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activity Schedules Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteOutdoorActivityScheduleByCustomerId(@RequestBody OutdoorActivityScheduleRequest outdoorActivityScheduleRequest){
        try{
            if(outdoorActivityScheduleService.checkIfOutdoorActivityScheduleExists(outdoorActivityScheduleRequest)) {
                outdoorActivityScheduleService.deleteOutdoorActivityScheduleByCustomerId(outdoorActivityScheduleRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Deleted");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Any Outdoor Activities Found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

}
