package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class UpdateFoodPriceRequest {
    private int foodId;
    private String foodName;
    private float price;

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public float getPrice() {
        return price;
    }
}
