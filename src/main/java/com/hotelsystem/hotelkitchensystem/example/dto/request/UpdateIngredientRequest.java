package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class UpdateIngredientRequest {
    private int ingredientId;
    private int qty;
    private String currentDate;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getQty() {
        return qty;
    }
}
