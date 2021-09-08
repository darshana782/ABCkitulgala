package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AddRecipeDetailsRequest {
    int[] fiIdList;
    int[] ingredientsQtyList;

    public int[] getFiIdList() {
        return fiIdList;
    }

    public int[] getIngredientsQtyList() {
        return ingredientsQtyList;
    }

    public void setFiIdList(int[] fiIdList) {
        this.fiIdList = fiIdList;
    }

    public void setIngredientsQtyList(int[] ingredientsQtyList) {
        this.ingredientsQtyList = ingredientsQtyList;
    }
}
