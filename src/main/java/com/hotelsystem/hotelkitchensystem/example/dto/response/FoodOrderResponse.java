package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class FoodOrderResponse {
    private int foodId;
    private String foodName;
    private int qty;

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQty() {
        return qty;
    }
}
