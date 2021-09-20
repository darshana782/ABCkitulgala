package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class StockReportResponse {
    private String upatedDate;
    private int ingredientId;
    private String ingredientName;
    private float updatedQty;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setUpatedDate(String upatedDate) {
        this.upatedDate = upatedDate;
    }

    public void setUpdatedQty(float updatedQty) {
        this.updatedQty = updatedQty;
    }


    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getUpatedDate() {
        return upatedDate;
    }

    public float getUpdatedQty() {
        return updatedQty;
    }
}
