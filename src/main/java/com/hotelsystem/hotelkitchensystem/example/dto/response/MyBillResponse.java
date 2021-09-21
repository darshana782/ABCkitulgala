package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class MyBillResponse {
    private double roomCharges;
    private double activityCharges;
    private double tax = 5000;
    private double foodCharges;

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public void setActivityCharges(double activityCharges) {
        this.activityCharges = activityCharges;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setFoodCharges(double foodCharges) {
        this.foodCharges = foodCharges;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public double getActivityCharges() {
        return activityCharges;
    }

    public double getTax() {
        return tax;
    }

    public double getFoodCharges() {
        return foodCharges;
    }
}
