package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class MyOrderResponse {
    private int orderId;
    private String foodName;
    private int qty;
    private double price;
    private int roomId;

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
