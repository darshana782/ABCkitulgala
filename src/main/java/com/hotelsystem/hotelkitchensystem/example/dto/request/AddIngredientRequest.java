package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddIngredientRequest {
    private String ingredientName;
    private int qty;
    private int reorderLevel;

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQty() {
        return qty;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }


    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}