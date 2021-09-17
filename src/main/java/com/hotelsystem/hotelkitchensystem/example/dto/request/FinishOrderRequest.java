package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class FinishOrderRequest {
    private int userId;
    private int orderId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }
}
