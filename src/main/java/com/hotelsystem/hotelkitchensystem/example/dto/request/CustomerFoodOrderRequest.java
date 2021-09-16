package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class CustomerFoodOrderRequest {
    int customerId;
    int[] foIdList;
    int[] qtyList;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int[] getFoIdList() {
        return foIdList;
    }

    public int[] getQtyList() {
        return qtyList;
    }

    public void setFoIdList(int[] foIdList) {
        this.foIdList = foIdList;
    }

    public void setQtyList(int[] qtyList) {
        this.qtyList = qtyList;
    }
}
