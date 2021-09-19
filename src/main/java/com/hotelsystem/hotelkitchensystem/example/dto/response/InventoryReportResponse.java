package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class InventoryReportResponse {
    private int ingredientId;
    private String ingredientName;
    private int availableQty;
    private int usedQty;
//    private String date;

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public void setUsedQty(int usedQty) {
        this.usedQty = usedQty;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public int getUsedQty() {
        return usedQty;
    }
//
//    public String getDate() {
//        return date;
//    }
}
