package com.hotelsystem.hotelkitchensystem.example.model;

import java.util.Date;

public class IngredientsReport {
    private int ID;
    private int ingredientId;
    private String ingredientName;
    private int changedQty;
    private Date changeDate;
    private String status;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setChangedQty(int changedQty) {
        this.changedQty = changedQty;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getChangedQty() {
        return changedQty;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public String getStatus() {
        return status;
    }
}
