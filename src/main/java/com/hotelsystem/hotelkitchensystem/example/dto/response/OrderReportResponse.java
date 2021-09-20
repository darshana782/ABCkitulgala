package com.hotelsystem.hotelkitchensystem.example.dto.response;

public class OrderReportResponse {
    private String orderDate;
    private int orderId;
    private int roomId;
    private String customerName;
    private String[] orderedFoods;
    private int[] orderedFoodQty;
    private int assignedStewardId;
    private String assignedStewardName;
    private float orderPrice;

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public void setOrderedFoods(String[] orderedFoods) {
        this.orderedFoods = orderedFoods;
    }

    public void setOrderedFoodQty(int[] orderedFoodQty) {
        this.orderedFoodQty = orderedFoodQty;
    }

    public void setAssignedStewardId(int assignedStewardId) {
        this.assignedStewardId = assignedStewardId;
    }

    public void setAssignedStewardName(String assignedStewardName) {
        this.assignedStewardName = assignedStewardName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String[] getOrderedFoods() {
        return orderedFoods;
    }

    public int[] getOrderedFoodQty() {
        return orderedFoodQty;
    }

    public int getAssignedStewardId() {
        return assignedStewardId;
    }

    public String getAssignedStewardName() {
        return assignedStewardName;
    }
}
