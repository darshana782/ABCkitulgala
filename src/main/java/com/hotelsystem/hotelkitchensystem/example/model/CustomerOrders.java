package com.hotelsystem.hotelkitchensystem.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_Orders")
public class CustomerOrders {

    @Id
    @GeneratedValue
    private int orderId;
    private int customerId;
    private int roomId;
    private String orderDate;
    private String orderTime;
    private String status;
    private int assignedStewardId;

    public int getAssignedStewardId() {
        return assignedStewardId;
    }

    public void setAssignedStewardId(int assignedStewardId) {
        this.assignedStewardId = assignedStewardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public static void setCustomerId(int customerId) {
    }

    public int getRoomId() {
        return roomId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
