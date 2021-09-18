package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class UpdateIngredientRequest {
    private int ingredientId;
    private int qty;

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
