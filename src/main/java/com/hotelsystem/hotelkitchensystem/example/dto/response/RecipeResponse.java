package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class RecipeResponse {
    private int fiId;
    private int ingredientId;
    private String ingredientName;

    public void setFiId(int fiId) {
        this.fiId = fiId;
    }

    public int getFiId() {
        return fiId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
