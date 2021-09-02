package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddFoodIngredientRequest {
//    private int foodId;
    private String foodName;
    int[] foodIngredients;

//    public int getFoodId() {
//        return foodId;
//    }

    public String getFoodName() {
        return foodName;
    }

    public int[] getFoodIngredients() {
        return foodIngredients;
    }

//    public void setFoodId(int foodId) {
//        this.foodId = foodId;
//    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodIngredients(int[] foodIngredients) {
        this.foodIngredients = foodIngredients;
    }
}


