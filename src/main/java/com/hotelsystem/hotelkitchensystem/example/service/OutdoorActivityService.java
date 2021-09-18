package com.hotelsystem.hotelkitchensystem.example.service;

import com.hotelsystem.hotelkitchensystem.example.dto.request.OutdoorActivityRequest;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivity;
import com.hotelsystem.hotelkitchensystem.example.repository.OutdoorActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutdoorActivityService {

    @Autowired
    private final OutdoorActivityRepository outdoorActivityRepository;

    public OutdoorActivityService(OutdoorActivityRepository outdoorActivityRepository) {
        this.outdoorActivityRepository = outdoorActivityRepository;
    }

    public boolean checkIfOutdoorActivityExists(OutdoorActivityRequest outdoorActivityRequest) throws Exception{
        try{
            OutdoorActivity outdoorActivity = outdoorActivityRepository
                    .findByOutdoorActivityName(outdoorActivityRequest.getOutdoorActivityName());
            return outdoorActivity != null;
        }catch (Exception e){
            return true;
        }
    }

    public void addOutdoorActivity(OutdoorActivityRequest outdoorActivityRequest) throws Exception{
        OutdoorActivity outdoorActivity = new OutdoorActivity();
        outdoorActivity.setOutdoorActivityName(outdoorActivityRequest.getOutdoorActivityName());
        outdoorActivityRepository.save(outdoorActivity);
    }

    public List<OutdoorActivity> getAllOutdoorActivities() throws Exception {
        return outdoorActivityRepository.findAll();
    }

    public void deleteOutdoorActivityById(OutdoorActivityRequest outdoorActivityRequest) throws Exception{
        outdoorActivityRepository.deleteById(outdoorActivityRequest.getOutdoorActivityId());
    }

}
