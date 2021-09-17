package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class StewardTaskResponse {
    private int orderId;
    private int roomId;
    private String customerName;

    public int getOrderId() {
        return orderId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
