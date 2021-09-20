package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.model.Activity;
import com.hotelsystem.hotelkitchensystem.example.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public Activity saveActivity(Activity activity){
        return repository.save(activity);
    }

    public List<Activity> getActivity(){
        return repository.findAll();
    }

}
