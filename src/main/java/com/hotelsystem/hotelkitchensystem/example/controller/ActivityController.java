package com.hotelsystem.hotelkitchensystem.example.controller;


import com.hotelsystem.hotelkitchensystem.example.model.Activity;
import com.hotelsystem.hotelkitchensystem.example.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3030")
@RequestMapping("/manager/activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService service;

    @PostMapping("/addActivity")
    public Activity addActivity(@RequestBody Activity activity){
        return service.saveActivity(activity);
    }

    @GetMapping("/viewActivity")
    public List<Activity> viewActivity(){
        return service.getActivity();
    }

}
