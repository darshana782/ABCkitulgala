package com.hotelsystem.hotelkitchensystem.example.dto.response;

import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;
import com.hotelsystem.hotelkitchensystem.example.model.OutdoorActivity;

import java.util.HashMap;
import java.util.List;

public class AvailableOutdoorActivityResponse {

    private OutdoorActivity outdoorActivity;
    private HashMap<ScheduleTimeSlot,Integer> availableSlots;

    public OutdoorActivity getOutdoorActivity() {
        return outdoorActivity;
    }

    public void setOutdoorActivity(OutdoorActivity outdoorActivity) {
        this.outdoorActivity = outdoorActivity;
    }

    public HashMap<ScheduleTimeSlot, Integer> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(HashMap<ScheduleTimeSlot, Integer> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
