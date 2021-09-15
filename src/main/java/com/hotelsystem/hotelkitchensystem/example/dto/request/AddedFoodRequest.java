package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddedFoodRequest {
    int[] IdOfOrderedFoods;

    public int[] getIdOfOrderedFoods() {
        return IdOfOrderedFoods;
    }

    public void setIdOfOrderedFoods(int[] idOfOrderedFoods) {
        IdOfOrderedFoods = idOfOrderedFoods;
    }
}
