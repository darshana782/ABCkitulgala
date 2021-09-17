package com.hotelsystem.hotelkitchensystem.example.dto.request;

public class AssignStewardRequest {
    private int empId;
    private int orderId;

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getEmpId() {
        return empId;
    }

    public int getOrderId() {
        return orderId;
    }
}
