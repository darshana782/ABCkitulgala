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
}
