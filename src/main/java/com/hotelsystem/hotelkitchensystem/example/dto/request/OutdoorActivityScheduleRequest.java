package com.hotelsystem.hotelkitchensystem.example.dto.request;

import com.hotelsystem.hotelkitchensystem.example.enums.ScheduleTimeSlot;

import java.util.Date;

public class OutdoorActivityScheduleRequest {

    private int outdoorActivityScheduleId;
    private int customerId;
    private int outdoorActivityId;
    private ScheduleTimeSlot scheduleTimeSlot;
    private Date scheduledDate;
    private boolean completed;

    public int getOutdoorActivityScheduleId() {
        return outdoorActivityScheduleId;
    }

    public void setOutdoorActivityScheduleId(int outdoorActivityScheduleId) {
        this.outdoorActivityScheduleId = outdoorActivityScheduleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOutdoorActivityId() {
        return outdoorActivityId;
    }

    public void setOutdoorActivityId(int outdoorActivityId) {
        this.outdoorActivityId = outdoorActivityId;
    }

    public ScheduleTimeSlot getScheduleTimeSlot() {
        return scheduleTimeSlot;
    }

    public void setScheduleTimeSlot(ScheduleTimeSlot scheduleTimeSlot) {
        this.scheduleTimeSlot = scheduleTimeSlot;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

