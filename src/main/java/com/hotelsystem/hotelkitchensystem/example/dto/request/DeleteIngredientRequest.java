package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class DeleteIngredientRequest {
    private String currentDate;

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
