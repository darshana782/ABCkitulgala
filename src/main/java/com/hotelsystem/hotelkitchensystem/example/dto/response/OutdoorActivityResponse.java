package com.hotelsystem.hotelkitchensystem.example.dto.response;

import javax.persistence.Column;

public class OutdoorActivityResponse {

    private int outdoorActivityId;
    private String outdoorActivityName;

    public int getOutdoorActivityId() {
        return outdoorActivityId;
    }

    public void setOutdoorActivityId(int outdoorActivityId) {
        this.outdoorActivityId = outdoorActivityId;
    }

    public String getOutdoorActivityName() {
        return outdoorActivityName;
    }

    public void setOutdoorActivityName(String outdoorActivityName) {
        this.outdoorActivityName = outdoorActivityName;
    }

}

